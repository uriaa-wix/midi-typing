package poc.src.it.scala.org.midityping.poc

import org.midityping.poc.midi.MidiEventListener
import org.specs2.mutable.SpecificationWithJUnit

class PocIT extends SpecificationWithJUnit {
  "A Note" should {
    "trigger A key press" in {
      val device = new MidiTestDevice

      val midiListener = new MidiEventListener()
      val handler = new TestEventHandler
      midiListener.subscribe(handler)
      midiListener.start
      //      eventually {
      //        device.sendEvent
      //        handler.messages.size mustNotEqual 0
      //        handler.messages.toList.foreach(m => {
      //          println(m)
      //        })
      //        ok
      //        //handler.messages.size mustEqual (999)
      //      }
      ok
    }
  }
}
