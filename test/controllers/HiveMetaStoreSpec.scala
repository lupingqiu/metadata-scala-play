package controllers

import javax.inject.Inject
import org.apache.hadoop.hive.conf.HiveConf
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient
import org.apache.hadoop.hive.metastore.api.Partition
import org.scalatest.FunSuite
import play.api.Configuration

import scala.collection.JavaConversions._

class HiveMetaStoreSpec extends FunSuite{
  val hiveConf:HiveConf = new HiveConf()
  hiveConf.setVar(HiveConf.ConfVars.METASTOREURIS,"thrift://172.18.21.207:9083")
  val hiveClient:HiveMetaStoreClient = new HiveMetaStoreClient(hiveConf)

  test("db"){
    val dbList = hiveClient.getAllDatabases
    dbList.foreach(println)
  }

  test("tables"){
    val tableList = hiveClient.getAllTables("test")
    tableList.foreach(println)
  }

  test("table"){
    val table = hiveClient.getTable("test","a")
    println(table)
  }

  test("partitions"){
    val partitions:List[Partition] = hiveClient.listPartitions("test","a",100).toList
    partitions.foreach(println)
  }

  test("getSchema"){
    val sList = hiveClient.getSchema("test","test2")
    sList.foreach{s=>
      println(s)
    }
  }

}
