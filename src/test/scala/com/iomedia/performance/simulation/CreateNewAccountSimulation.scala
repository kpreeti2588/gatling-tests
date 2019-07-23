package com.iomedia.performance.simulation

import com.iomedia.performance.util._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import com.iomedia.performance.scenarios.{CreateAccount}

class CreateNewAccountSimulation extends Simulation {

  val httpConf = http.baseURL(Environemnt.baseURL)
                      .headers(Headers.commonHeader)

  val createAccountScenarios = List(

    CreateAccount.createUser.inject(
      atOnceUsers(1),
    ))

  setUp(createAccountScenarios)
    
    .protocols(httpConf)
    .maxDuration(1 minutes)
    .assertions(
      global.responseTime.max.lessThan(Environemnt.maxResponseTime.toInt)
    )
}