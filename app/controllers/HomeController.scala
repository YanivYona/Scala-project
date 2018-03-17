package controllers

import javax.inject._
import scalaj.http._
import play.api.mvc._
import com.google.gson.Gson


case class Coupon(product: String,description :String,image:String)
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  private val URL =  "http://localhost:9000/mydb"
  private val gson = new Gson

  def index() = Action { implicit request: Request[AnyContent] =>
    val result = Http(URL).asString
    val coupons = gson.fromJson(result.body, classOf[Array[Coupon]])
    Ok(views.html.index(coupons))
  }
}
