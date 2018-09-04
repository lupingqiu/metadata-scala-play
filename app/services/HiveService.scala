package services

import javax.inject.Inject
import models.{Field, Table}
import org.apache.hadoop.hive.conf.HiveConf
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient
import play.api.Configuration

import scala.collection.JavaConversions._

class HiveService @Inject()(config:Configuration) {

  def client:HiveMetaStoreClient={
    val hiveConf:HiveConf = new HiveConf()
    hiveConf.setVar(HiveConf.ConfVars.METASTOREURIS,config.get[String]("hive.metastore.uris"))
    new HiveMetaStoreClient(hiveConf)
  }

  def databases(): List[String]={
    val c = client
    try{
      c.getAllDatabases.toList
    }catch {
      case e:Exception=> throw e
    } finally {
      if(c!=null) c.close()
    }
  }

  def tables(dbName:String):List[String]={
    val c = client
    try{
      c.getAllTables(dbName).toList
    }catch {
      case e:Exception=> throw e
    } finally {
      if(c!=null) c.close()
    }
  }

  def table(dbName:String,tableName:String): Table={
    val c = client
    try{
      val t = c.getTable(dbName,tableName)
      Table(t.getTableName,t.getDbName,t.getOwner,t.getCreateTime,t.getLastAccessTime,t.getPartitionKeys.toList.map(_.getName))
    }catch {
      case e:Exception=> throw e
    } finally {
      if(c!=null) c.close()
    }
  }

  def fields(dbName:String,tableName:String):List[Field]={
    val c = client
    try{
      val filedList = c.getSchema(dbName,tableName).toList
      filedList.map{f=>
        Field(f.getName,f.getType,f.getComment)
      }
    }catch {
      case e:Exception=> throw e
    } finally {
      if(c!=null) c.close()
    }
  }
}
