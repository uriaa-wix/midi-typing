package org.midityping.poc.mapping.storage

import java.io.File

import org.midityping.poc.mapping.Mapping

class FileMappingStorage(root: File) extends MappingStorage {
  override def load(): Seq[Mapping] = {
    root.listFiles().map(file => CustomMappingLoader.load(file))
  }
}
