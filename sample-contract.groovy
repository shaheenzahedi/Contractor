org.springframework.cloud.contract.spec.Contract.make {
    request { // (1)
        method 'POST' // (2)
        url '/test/header' // (3)
    }
    response { // (6)
        status OK() // (7)
        body([ // (8)
               login: "sample body login"
        ])
        headers { // (9)
            header('Content-Accepted' : 'Application/Json')
        }
    }
}
