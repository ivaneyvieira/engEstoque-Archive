package br.com.engecopi.framework.model

import io.ebean.annotation.Platform
import io.ebean.dbmigration.DbMigration
import java.io.IOException

object MainDbMigration {
  @Throws(IOException::class)
  @JvmStatic
  fun main(args: Array<String>) {
    // System.setProperty("ddl.migration.generate", "true")
    val home = System.getenv("HOME")
    val fileName = System.getenv("EBEAN_PROPS") ?: "$home/ebean.properties"
    System.setProperty("ebean.props.file", fileName)

    System.setProperty("ddl.migration.name", "support end dating")
    System.setProperty("ddl.migration.version", "1.42")

    //System.setProperty("ddl.migration.pendingDropsFor", "1.39")
    val migration = DbMigration.create()
    migration.setStrictMode(false)
    migration.setPlatform(Platform.MYSQL)
    //migration.setVersion("1.12")
    System.setProperty("disableTestProperties", "true")
    migration.generateMigration()
    
    // starting EbeanServer triggers the apply of migrations
    // ... when ebean.migration.run=true
    //Transaction.server
    
    System.out.println("done")
  }
}
