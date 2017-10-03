package controllers

import javax.inject._

import akka.actor.ActorSystem
import play.api.mvc._
import botkop.nn.akka.gates._
import botkop.nn.akka.optimizers._
import botkop.nn.akka.CostFunctions._

@Singleton
class HomeController @Inject()(system: ActorSystem) extends Controller {

  def optimizer() = Nesterov(learningRate = 0.1)

  def index = Action {

    val (input, output) =
      ((Linear + Relu) * 2)
        .withDimensions(784, 50, 10)
        .withOptimizer(optimizer)
        .withCostFunction(softmaxCost)
        .withRegularization(1e-5)
        .withMiniBatchSize(16)
        .init()

    Ok(views.html.index("Your new application is ready."))
  }

}
