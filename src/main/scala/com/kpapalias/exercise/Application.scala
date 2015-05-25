package com.kpapalias.exercise

import java.io.{FileNotFoundException, IOException}

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Application {

  val fileName = "input.txt"

  def main(args: Array[String]) {
    try {
      val (room, instructions) = parseFile(Source.fromFile(fileName))
      val spotsCleaned = applyInstructions(room, instructions)
      outputResult(room.hooverPosition, spotsCleaned)
    } catch {
      case ex: FileNotFoundException => println("Couldn't find the file: " + fileName)
      case ex: IOException => println("Had an IOException while trying to read file: " + fileName)
    }

  }

  def parseFile(file: Source): (Room, Array[Char]) = {
    val dirt = ListBuffer[(Int, Int)]()
    var instructions = Array[Char]()

    val lines = file.getLines()
    val dimensions: (Int, Int) = lines.next
    val hooverCoordinates: (Int, Int) = lines.next

    for (line <- lines) {
      line.split(" ") match {
        case Array(x, y) => dirt += Tuple2(x.toInt, y.toInt)
        case x => instructions = x.flatten
      }
    }

    val room = Room(dimensions, dirt, hooverCoordinates)

    (room, instructions)
  }

  def applyInstructions(room: Room, instructions: Array[Char]): Int = {
    var spotsCleaned = 0

    instructions.foreach {
      i =>
        room.moveHoover(i)
        if (room.hoover) spotsCleaned += 1
    }
    spotsCleaned
  }

  def outputResult(hooverPosition: (Int, Int), spotsCleaned: Int): Unit = {
    println(hooverPosition)
    println(spotsCleaned)
  }

  implicit def lineToPair(line: String): (Int, Int) = {
    line.split(" ") match {
      case Array(x, y) => Tuple2(x.toInt, y.toInt)
    }
  }
}
