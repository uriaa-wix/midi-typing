package org.midityping.poc.logging

import org.slf4j.{Logger, LoggerFactory}

object aLogger {
  def forClass(clazz: Class[_]): Logger = {
    LoggerFactory.getLogger(clazz)
  }
}
