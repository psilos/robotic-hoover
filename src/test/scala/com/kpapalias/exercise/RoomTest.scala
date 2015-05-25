
package com.kpapalias.exercise

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

class RoomTest extends FunSuite {

  test("Hoover can move North on room") {
    // Given
    val hooverPosition = (1, 2)
    val room = Room((5,5), ListBuffer(), hooverPosition)

    // When
    val newRoom = room moveHoover 'N'

    // Then
    assert(room.hooverPosition === (1,3))
  }

  test("Hoover can't move North if it's on the wall") {
    // Given
    val hooverPosition = (1, 5)
    val room = Room((5,5), ListBuffer(), hooverPosition)

    // When
    val newRoom = room moveHoover 'N'

    // Then
    assert(room.hooverPosition === hooverPosition)
  }

  test("Hoover can move South on room") {
    // Given
    val hooverPosition = (1, 2)
    val room = Room((5,5), ListBuffer(), hooverPosition)

    // When
    val newRoom = room moveHoover 'S'

    // Then
    assert(room.hooverPosition === (1,1))
  }

  test("Hoover can't move South if it's on the wall") {
    // Given
    val hooverPosition = (1, 0)
    val room = Room((5,5), ListBuffer(), hooverPosition)

    // When
    val newRoom = room moveHoover 'S'

    // Then
    assert(room.hooverPosition === hooverPosition)
  }

  test("Hoover can move West on room") {
    // Given
    val hooverPosition = (1, 2)
    val room = Room((5,5), ListBuffer(), hooverPosition)

    // When
    val newRoom = room moveHoover 'W'

    // Then
    assert(room.hooverPosition === (0,2))
  }

  test("Hoover can't move West if it's on the wall") {
    // Given
    val hooverPosition = (0, 2)
    val room = Room((5,5), ListBuffer(), hooverPosition)

    // When
    val newRoom = room moveHoover 'W'

    // Then
    assert(room.hooverPosition === hooverPosition)
  }

  test("Hoover can move East on room") {
    // Given
    val hooverPosition = (1, 2)
    val room = Room((5,5), ListBuffer(), hooverPosition)

    // When
    val newRoom = room moveHoover 'E'

    // Then
    assert(room.hooverPosition === (2,2))
  }

  test("Hoover can't move East if it's on the wall") {
    // Given
    val hooverPosition = (5, 2)
    val room = Room((5,5), ListBuffer(), hooverPosition)

    // When
    val newRoom = room moveHoover 'E'

    // Then
    assert(room.hooverPosition === hooverPosition)
  }

  test("Hoover can clean up dirt if it is on a dirty spot") {
    // Given
    val hooverPosition = (1, 3)
    val dirtySpot = (1, 3)
    val room = Room((5,5), ListBuffer(dirtySpot), hooverPosition)

    // When
    val cleaned = room hoover

    // Then
    assert(room.hooverPosition === dirtySpot)
    assert(room.dirtySpots === ListBuffer())
    assert(cleaned === true)

  }

}
