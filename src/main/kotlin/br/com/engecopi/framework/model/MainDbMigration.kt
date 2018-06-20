package br.com.engecopi.framework.model

import io.ebean.annotation.Platform
import io.ebean.dbmigration.DbMigration
import java.io.IOException

object MainDbMigration {
  @Throws(IOException::class)
  @JvmStatic
  fun main(args: Array<String>) {
    System.setProperty("ddl.migration.name", "support end dating")
  
   //System.setProperty("ddl.migration.pendingDropsFor", "1.3")
    
    val migration = DbMigration.create()
    
    migration.setPlatform(Platform.MYSQL)
    migration.generateMigration()
  }
}
