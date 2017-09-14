package v1.career

import javax.inject.{Inject, Provider}

import play.api.MarkerContext

import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.json._

/**
  * DTO for displaying career information.
  */
case class careerResource(id: String, link: String, name: String, description: String)

object careerResource {

  /**
    * Mapping to write a PostResource out as a JSON value.
    */
  implicit val implicitWrites = new Writes[careerResource] {
    def writes(career: careerResource): JsValue = {
      Json.obj(
        "id" -> career.id,
        "link" -> career.link,
        "name" -> career.name,
        "description" -> career.description
      )
    }
  }
}

/**
  * Controls access to the backend data, returning [[careerResource]]
  */
class CareerResourceHandler @Inject()(
                                       routerProvider: Provider[CareerRouter],
                                       careerRepository: CareerRepository)(implicit ec: ExecutionContext) {

  def create(careerInput: CareerFormInput)(implicit mc: MarkerContext): Future[careerResource] = {
    val data = CareerData(CareerId("999"), careerInput.name, careerInput.description)
    // We don't actually create the post, so return what we have
    careerRepository.create(data).map { id =>
      createCareerResource(data)
    }
  }

  def lookup(id: String)(implicit mc: MarkerContext): Future[Option[careerResource]] = {
    val careerFuture = careerRepository.get(CareerId(id))
    careerFuture.map { maybePostData =>
      maybePostData.map { postData =>
        createCareerResource(postData)
      }
    }
  }

  def find(implicit mc: MarkerContext): Future[Iterable[careerResource]] = {
    careerRepository.list().map { postDataList =>
      postDataList.map(postData => createCareerResource(postData))
    }
  }

  private def createCareerResource(career: CareerData): careerResource = {
    careerResource(
      career.id.toString, routerProvider.get.link(career.id),
      career.name, career.description)
  }

}
