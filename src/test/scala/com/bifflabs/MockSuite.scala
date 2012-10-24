package com.bifflabs

import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.scalatest.matchers.ShouldMatchers
import org.mockito.Mockito.when

trait MockingBird {

  def sing(input: List[String]): String

}

class MockSuite extends FunSuite with ShouldMatchers with MockitoSugar {

  val iWannaRock = List("I wanna Rock")
  val beatingDeadHorse = List("Sometimes I feel like I'm beating a dead horse")

  val mockMockingBird = mock[MockingBird]
  when(mockMockingBird.sing(beatingDeadHorse)).thenReturn("An I don't know why you'd be bringing me down")
  when(mockMockingBird.sing(iWannaRock)).thenReturn("Rock!")

  test("A mock should sing") {
    assert(mockMockingBird.sing(beatingDeadHorse) === "An I don't know why you'd be bringing me down")
    assert(mockMockingBird.sing(iWannaRock) === "Rock!")
  }

}
