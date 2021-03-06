package org.midityping.poc.midi

import javax.sound.midi.{MidiMessage, Receiver, ShortMessage}
import org.midityping.poc.logging.aLogger

import scala.util.Try

class MidiReceiver(listener: MidiEventListener) extends Receiver {
  val logger = aLogger.forClass(getClass)

  override def send(message: MidiMessage, timeStamp: Long): Unit = {
    Try {
      message match {
        case sm: ShortMessage =>
          logger.trace(s"message: ${sm.getCommand} ${sm.getData1} ${sm.getData2}")
          listener.message(sm)
        case _ =>
      }
    }.recover {
      case ex => logger.error(ex.getMessage, ex)
    }
  }

  override def close(): Unit = {

  }
}
