package com.bifflabs

    import org.scalatest.FunSuite
    import org.scalatest.mock.MockitoSugar
    import org.mockito.Mockito.when

    trait MockingBird {
      //Assertion does not fail when input does not have Type param
      def sing(input: Set[String]): String
    }

    trait MockingBirdWithStrings {
      //Assertion does not fail when input does not have Type param
      def sing(input: String): String
    }

    class MockSuite extends FunSuite with MockitoSugar {

      // Expected parameter must have ≥ 1 type param
      // for described behavior to be expressed, hence the Set
      val iWannaRock = Set("I wanna Rock")
      val rock = "Rock!"

      val wereNotGonnaTakeIt = Set("We're not gonna take it")
      val No = "No! We ain't gonna take it"

      test("A mock should match on parameter but isn't") {

        val mockMockingBird = mock[MockingBird]
        when(mockMockingBird.sing(iWannaRock)).thenReturn(rock)
        //Appears to return this whenever any Set is passed to sing
        when(mockMockingBird.sing(wereNotGonnaTakeIt)).thenReturn(No)

        // Succeeds because it was last
        assert(mockMockingBird.sing(wereNotGonnaTakeIt) === No)
        // Fails because the mock returns value in bringinMeDown
        assert(mockMockingBird.sing(iWannaRock) === rock)
      }

  test("A mock should match on parameter and does type of parameter has no type params") {

    val mockMockingBirdWithStrings = mock[MockingBirdWithStrings]
    when(mockMockingBirdWithStrings.sing(iWannaRock.head)).thenReturn(rock)
    when(mockMockingBirdWithStrings.sing(wereNotGonnaTakeIt.head)).thenReturn(No)

    assert(mockMockingBirdWithStrings.sing("") === null)
    assert(mockMockingBirdWithStrings.sing(iWannaRock.head) === rock)
    assert(mockMockingBirdWithStrings.sing(wereNotGonnaTakeIt.head) === No)
  }

}
