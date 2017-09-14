package v1.career

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import play.api.libs.concurrent.CustomExecutionContext
import play.api.{Logger, MarkerContext}

import scala.concurrent.Future

final case class CareerData(id: CareerId, name: String, description: String)

class CareerId private(val underlying: Int) extends AnyVal {
  override def toString: String = underlying.toString
}

object CareerId {
  def apply(raw: String): CareerId = {
    require(raw != null)
    new CareerId(Integer.parseInt(raw))
  }
}


class CareerExecutionContext @Inject()(actorSystem: ActorSystem) extends CustomExecutionContext(actorSystem, "repository.dispatcher")

/**
  * A pure non-blocking interface for the CareerRepository.
  */
trait CareerRepository {
  def create(data: CareerData)(implicit mc: MarkerContext): Future[CareerId]

  def list()(implicit mc: MarkerContext): Future[Iterable[CareerData]]

  def get(id: CareerId)(implicit mc: MarkerContext): Future[Option[CareerData]]
}

/**
  * A trivial implementation for the Post Repository.
  *
  * A custom execution context is used here to establish that blocking operations should be
  * executed in a different thread than Play's ExecutionContext, which is used for CPU bound tasks
  * such as rendering.
  */
@Singleton
class CareerRepositoryImpl @Inject()()(implicit ec: CareerExecutionContext) extends CareerRepository {

  private val logger = Logger(this.getClass)

  private val careerList = List(
    CareerData(CareerId("1"),
      "Tecnicatura universitaria en programacion informatica",
      "tpi"),
    CareerData(CareerId("2"),
      "Licenciatura en informatica con orientacion en desarrollo de software",
      "lids")
  )

  override def list()(implicit mc: MarkerContext): Future[Iterable[CareerData]] = {
    Future {
      logger.trace(s"list: ")
      careerList
    }
  }

  override def get(id: CareerId)(implicit mc: MarkerContext): Future[Option[CareerData]] = {
    Future {
      logger.trace(s"get: id = $id")
      careerList.find(post => post.id == id)
    }
  }

  def create(data: CareerData)(implicit mc: MarkerContext): Future[CareerId] = {
    Future {
      logger.trace(s"create: data = $data")
      data.id
    }
  }

}
