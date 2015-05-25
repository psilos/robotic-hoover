package com.kpapalias.exercise

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer
import scala.io.Source

class ApplicationTest extends FunSuite {

  test("Application can parse input.txt file and initialise Room") {
    // Given
    val source = Source.fromURL(getClass.getResource("/input.txt"))

    // When
    val (room, instructions) = Application.parseFile(source)

    // then
    assert(instructions === "NNESEESWNWW".toCharArray)
    assert(room.dimensions === (5, 5))
    assert(room.hooverPosition === (1, 2))
    assert(room.dirtySpots === List((1, 0), (2,2), (2,3)))
  }

  test("Applying instructions, should return the number of spots that have been cleaned") {
    // Given
    val hooverPosition = (1, 2)
    val dirtySpots = ListBuffer((1, 0), (2,2), (2,3))
    val room = Room((5,5), dirtySpots, hooverPosition)
    val instructions = "NNESEESWNWW".toCharArray

    // When
    val spotsCleaned = Application.applyInstructions(room, instructions)

    // Then
    assert(spotsCleaned === 1)
    assert(room.hooverPosition === (1,3))
  }

  test("Application Outputs the position of the Hoover and the number of patches that have been cleaned") {
    // Given
    val hooverPosition = (1, 3)
    val spotsCleaned = 1

    // When
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      Application.outputResult(hooverPosition, spotsCleaned)
    }

    // Then
    assert(stream.toString("UTF-8") === "(1,3)\n1\n")

  }

}
