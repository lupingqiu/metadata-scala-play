package controllers

import Entity.Person
import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    val l:List[String]= List("a","b","c")
    Ok(views.html.index(l=l)(p=Person("rube",31)))
  }

  def name() = Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.main(title = "rube")(content = null))
  }

  def flashOne = Action { implicit request =>
    Ok {
      request.flash.get("success").getOrElse("Welcome!")
    }
  }

  def flashTwo = Action {
    Redirect("/home").flashing(
      "success" -> "The item has been created")
  }
}
