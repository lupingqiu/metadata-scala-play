package models


case class Table(tableName:String,dbName:String,owner:String,createTime:Int,lastAccessTime:Int,partitionKeys:List[String])

case class Field(fieldName:String,fieldType:String,comment:String)

case class Partition(values:List[String],createTime:Int,lastAccessTime:Int,keys:List[String],compressed:Boolean,numFiles:Int)