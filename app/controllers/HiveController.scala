package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import services.HiveService

class HiveController @Inject()(cc: ControllerComponents,hiveService:HiveService) extends AbstractController(cc){

  def databases() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.databases(hiveService.databases()))
  }

  def tables(dbName:String) = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.tables(dbName)(hiveService.tables(dbName)))
  }

  def schema(dbName:String,tableName:String) = Action { implicit request: Request[AnyContent] =>
    val table= hiveService.table(dbName,tableName)
    val fields = hiveService.fields(dbName,tableName)
    Ok(views.html.schema(table)(fields))
  }
}
