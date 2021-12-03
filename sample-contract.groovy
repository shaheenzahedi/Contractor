org.springframework.cloud.contract.spec.Contract.make {
    request { // (1)
        method 'PUT' // (2)
        url '/fraudcheck' // (3)
        body([ // (4)
               clientId  : $(regex('[0-9]{10}')),
               loanAmount: 99999
        ])
        headers { // (5)
            contentType('application/vnd.fraud.v1+json')
        }
    }
    response { // (6)
        status OK() // (7)
        body([ // (8)
               fraudCheckStatus: "FRAUD",
               rejectionReason : "Amount too high"
        ])
        headers { // (9)
            contentType('application/vnd.fraud.v1+json')
        }
    }
}
