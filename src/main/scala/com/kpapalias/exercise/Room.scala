package com.kpapalias.exercise

import scala.collection.mutable.ListBuffer

case class Room(dimensions: (Int, Int), dirtySpots: ListBuffer[(Int, Int)], var hooverPosition: (Int, Int)) {

  def moveHoover(direction: Char) {
    hooverPosition match {
      case (x, y) =>
        
        direction match {
          case 'N' =>
            val newPosition = y + 1
            if (newPosition <= dimensions._2) {
              hooverPosition = (x, newPosition)
            }
          case 'S' =>
            val newPosition = y - 1
            if (newPosition >= 0) {
              hooverPosition = (x, newPosition)
            }
          case 'W' =>
            val newPosition = x - 1
            if (newPosition >= 0) {
              hooverPosition = (newPosition, y)
            }
          case 'E' =>
            val newPosition = x + 1
            if (newPosition <= dimensions._1) {
              hooverPosition = (newPosition, y)
            }
        }
    }
  }

  def hoover = {
    var cleanedDirtySpot = false
    if (dirtySpots.contains(hooverPosition)) {
      dirtySpots -= hooverPosition
      cleanedDirtySpot = true
    }
    cleanedDirtySpot
  }
}
