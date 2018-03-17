package coupon.conn

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import com.google.gson.Gson
import reactivemongo.play.json._
import play.modules.reactivemongo.json.collection._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import play.modules.reactivemongo.{ // ReactiveMongo Play2 plugin
  MongoController,
  ReactiveMongoApi,
  ReactiveMongoComponents
}

@Singleton
  class CouponController @Inject() (val reactiveMongoApi: ReactiveMongoApi)
  extends Controller with MongoController with ReactiveMongoComponents {

  def mongo = new server.MongoConn(reactiveMongoApi)
  def index = Action.async { implicit request =>
  mongo.find().map(coupons => Ok(Json.toJson(coupons)))
}
}
