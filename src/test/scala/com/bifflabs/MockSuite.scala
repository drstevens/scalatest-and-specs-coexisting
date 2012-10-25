package com.bifflabs

import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.scalatest.matchers.ShouldMatchers
import org.mockito.Mockito.when

trait MockingBird {

  //Assertion does not fail when input does not have Type param
  def sing(input: Set[String]): String

}

trait MockingBirdWithStrings {

  //Assertion does not fail when input does not have Type param
  def sing(input: String): String

}

class MockSuite extends FunSuite with ShouldMatchers with MockitoSugar {

  //Make sure it's not using object equals to match
  val (iWannaRock, rock) = (Set("I wanna Rock"), "Rock!")
  val (beatingDeadHorse, bringinMeDown) = (Set("Sometimes I feel like I'm beating a dead horse"), "An I don't know why you'd be bringing me down")

  val mockMockingBird = mock[MockingBird]
  when(mockMockingBird.sing(iWannaRock)).thenReturn(rock)

  //Appears to return this whenever any Set is passed to sing
  when(mockMockingBird.sing(beatingDeadHorse)).thenReturn(bringinMeDown)

  test("A mock should match on parameter but isn't") {
    assert(mockMockingBird.sing(Set.empty) === null)
    assert(mockMockingBird.sing(beatingDeadHorse) === bringinMeDown)
    assert(mockMockingBird.sing(iWannaRock) === rock)
  }

  val mockMockingBirdWithStrings = mock[MockingBirdWithStrings]
  when(mockMockingBirdWithStrings.sing("I wanna Rock")).thenReturn(rock)
  when(mockMockingBirdWithStrings.sing("Sometimes I feel like I'm beating a dead horse")).thenReturn(bringinMeDown)

  test("A mock should match on parameter and does type of parameter has no type params") {
    assert(mockMockingBirdWithStrings.sing("") === null)
    assert(mockMockingBirdWithStrings.sing("I wanna Rock") === rock)
    assert(mockMockingBirdWithStrings.sing("Sometimes I feel like I'm beating a dead horse") === bringinMeDown)
  }

}
