import {Verifier} from "@pact-foundation/pact";
import path from "path";
import {app} from "./server";
import {Blog} from "./models/Blog";

import "@testing-library/jest-dom/extend-expect";
import {
    mockedArchivedBlogpost,
    mockedBlogpost,
    mockedUpdatedBlogpost,
} from "../ui/pages/BlogPage/consumer-contract.spec";

export const mockedBlogpost = {
    id: 1,
    title: "Mocked Blogpost",
    categorydisplayvalue: "Test",
    categoryslug: "test",
    slug: "Test",
    date: "2021-05-28T09:04:55.343Z",
    image: "",
    email: null,
    instagram: null,
    twitter: null,
    github: null,
    facebook: null,
    blogpostcontent: {
        blocks: [
            {
                key: "csc33",
                data: {},
                text: "This is a dummy post",
                type: "unstyled",
                depth: 0,
                entityRanges: [],
                inlineStyleRanges: [],
            },
        ],
        entityMap: {},
    },
    isarchived: 0,
};

export const mockedUpdatedBlogpost = {
    id: 1,
    title: "Updated Mocked Blogpost",
    categorydisplayvalue: "Test",
    categoryslug: "test",
    slug: "updated-mocked-blogpost",
    blogpostcontent: {
        blocks: [
            {
                key: "csc33",
                data: {},
                text: "This text was updated",
                type: "unstyled",
                depth: 0,
                entityRanges: [],
                inlineStyleRanges: [],
            },
        ],
        entityMap: {},
    },
};

export const mockedArchivedBlogpost = {
    id: 1,
    title: "Mocked Blogpost",
    categorydisplayvalue: "Test",
    categoryslug: "test",
    slug: "mocked-blogpost",
    date: "2021-05-28T09:04:55.343Z",
    image: "",
    email: null,
    instagram: null,
    twitter: null,
    github: null,
    facebook: null,
    blogpostcontent: {
        blocks: [
            {
                key: "csc33",
                data: {},
                text: "This is a dummy post",
                type: "unstyled",
                depth: 0,
                entityRanges: [],
                inlineStyleRanges: [],
            },
        ],
        entityMap: {},
    },
    isarchived: 1,
};


jest.mock("./models/Blog", () => {
    return {
        Blog: {
            fetchAllBlogposts: jest.fn(),
            updateBlogpost: jest.fn(),
            archiveSingleBlogpost: jest.fn(),
            fetchAllBlogpostsByCategorySlug: jest.fn(),
            fetchAllCategories: jest.fn(),
            fetchSingleBlogpost: jest.fn(),
            deleteSingleBlogpost: jest.fn(),
        }
    };
});

jest.mock('./models/Library', () => {
    return {
        Library: {
            fetchAllComponents: jest.fn(),
            fetchSingleComponent: jest.fn(),
        },
        Tags: {
            fetchTags: jest.fn()
        }
    }
})

const distinctCategories = {
    categorydisplayvalue: "Testing Category",
    categoryslug: "testing-category",
};

describe("Pact Verification", () => {
    const server = app.listen(6666, "0000", () =>
        console.log("server running for api testing")
    );

    test("should validate the expectations of our consumer", () => {
        Blog.fetchAllBlogposts.mockReturnValue([mockedBlogpost, mockedBlogpost, mockedBlogpost]);
        Blog.updateBlogpost.mockReturnValue(mockedUpdatedBlogpost);
        Blog.fetchSingleBlogpost.mockReturnValue(mockedBlogpost);
        Blog.fetchAllBlogpostsByCategorySlug.mockReturnValue(mockedBlogpost);
        Blog.archiveSingleBlogpost.mockReturnValue(mockedArchivedBlogpost);
        Blog.fetchAllCategories.mockReturnValue([
            distinctCategories,
            distinctCategories,
            distinctCategories,
            distinctCategories,
        ]);
        Blog.deleteSingleBlogpost.mockReturnValue("successfully deleted blogpost");

        const opts = {
            provider: "BlogTestingProvider",
            providerBaseUrl: "http://localhost:6666",
            // pactBrokerUrl: process.env.PACT_BROKER_URL,
            //  pactBrokerToken: process.env.PACT_BROKER_TOKEN,
            pactUrls: [
                path.resolve(
                    process.cwd(),
                    "src/__tests__/pact/blogtestingconsumer-blogtestingprovider.json"
                ),
            ],
            publishVerificationResult: true,
            providerVersion: "1.0.0",
            logLevel: "INFO",
        };

        return new Verifier(opts)
            .verifyProvider()
            .then((output) => {
                server.close();
                console.log("pact verification complete !");
                console.log(output);
            })
            .catch((error) => {
                server.close();
                fail(error);
            });
    });
});