package server

import play.api.libs.json.{JsObject, Json}
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.ReadPreference
import play.modules.reactivemongo.json._
import scala.concurrent.{ExecutionContext, Future}

trait Mongo {
  def find()(implicit ec: ExecutionContext): Future[List[JsObject]]
}

class MongoConn(reactiveMongoApi: ReactiveMongoApi) extends Mongo {
  protected def collection = reactiveMongoApi.db.collection[JSONCollection]("mydb")
  def find()(implicit ec: ExecutionContext): Future[List[JsObject]] = collection.find(Json.obj()).cursor[JsObject](ReadPreference.Primary).collect[List]()
}
