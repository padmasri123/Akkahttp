import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.{HttpApp,Route}
import akka.http.scaladsl.settings.ServerSettings
import com.typesafe.config.ConfigFactory

object SimpleHttpServer extends HttpApp {
  override def routes: Route =
    pathPrefix("simple") {
      path("num" / IntNumber) { num =>
        get {  //curl -i -v -X GET http://0.0.0.0:8080/simple/num/1
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, s"Received Get Req with num: $num"))
        }
      }
    }
}

object Server extends App {
  SimpleHttpServer.startServer("0.0.0.0", 8080, ServerSettings(ConfigFactory.load))
}