package com.example.demo

import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.State
import au.com.dius.pact.provider.junit.loader.PactBroker
import au.com.dius.pact.provider.junit.target.Target
import au.com.dius.pact.provider.junit.target.TestTarget
import au.com.dius.pact.provider.spring.SpringRestPactRunner
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest

@RunWith(SpringRestPactRunner::class)
@Provider("user-data-provider")
@PactBroker(protocol = "http", host = "localhost", port = "80")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserDataProviderContractIT {
    @TestTarget
    @JvmField
    final val target: Target = SpringBootHttpTarget()

    // only needed for Ajax Consumer
    @State("some user available")
    fun userAvailable() {}
}