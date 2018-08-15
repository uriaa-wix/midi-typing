package org.midityping.poc.app

import java.io.File

import org.midityping.poc.actions.{DefaultActionExecutor, DefaultActionFactory}
import org.midityping.poc.mapping.Mapper
import org.midityping.poc.mapping.storage.FileMappingStorage
import org.midityping.poc.midi.MidiEventListener
import org.midityping.poc.system.{DefaultEventHandler, MidiTypingSystem}

object MidiTypingApp {
  def main(args: Array[String]): Unit = {
    val mapper = new Mapper
    val eventListener = new MidiEventListener
    val actionFactory = new DefaultActionFactory
    val actionExecutor = new DefaultActionExecutor
    val eventHandler = new DefaultEventHandler(mapper, actionFactory, actionExecutor)
    val system = new MidiTypingSystem(eventListener, eventHandler, mapper, actionFactory, actionExecutor)

    val storageDir = new File("midi-typing/mappings/")
    if (!storageDir.exists()) {
      log("creating dir " + storageDir.getAbsolutePath)
      val created = storageDir.mkdirs()
      log(s"dir ${storageDir.getAbsolutePath} created? $created")
    }
    val mappingStorage = new FileMappingStorage(storageDir)
    mappingStorage.load().foreach(mapping => {
      system.loadMapping(mapping)
    })

    system.start
    Thread.sleep(600000)
  }

  def log(s: String) = {
    println(s)
  }
}
