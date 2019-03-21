package agua

object AguaTest extends App{
  import Agua._

  val s = Superficie(8,8)
  s.nivel(3,3) = 7

  for( _ <- 0 to 10 ){
    s.dump()
    println()
    s.paso(0.1)
  }

  


  println("fin")

}
