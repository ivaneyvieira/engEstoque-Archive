package br.com.engecopi.framework.model

import io.ebean.annotation.Platform
import io.ebean.dbmigration.DbMigration
import java.io.IOException

object MainDbMigration {
  @Throws(IOException::class)
  @JvmStatic
  fun main(args: Array<String>) {
    System.setProperty("ddl.migration.generate", "true")
    
    System.setProperty("ddl.migration.name", "support end dating")
    
    val migration = DbMigration.create()
    migration.setStrictMode(false)
    migration.setPlatform(Platform.MYSQL)
    migration.generateMigration()
    
    System.setProperty("disableTestProperties", "true")
    
    // starting EbeanServer triggers the apply of migrations
    // ... when ebean.migration.run=true
    // Ebean.getDefaultServer()
    
    System.out.println("done")
  }
}
