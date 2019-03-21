package agua

object Agua{


  type Nivel = Double
  type Velocidad = Double
  type Rozamiento = Double
  type Fuerza = Double
  type Paso = Double

  case class Superficie( ancho: Int, alto: Int ){

    private val niveles = Array.fill(ancho,alto){
      0.asInstanceOf[Nivel]
    }

    private val velocidades = Array.fill(ancho,alto){
      0.asInstanceOf[Velocidad]
    }


    def dump(f: (String)=>Unit = print ) = {
      for( x <- 0 until ancho ){
        for( y <- 0 until alto ){
          f( f"\t${nivel(x,y)}%4.2f" )
        }
        f("\n")
      }
    }

    object nivel{
      def apply(x:Int, y:Int) = niveles(x)(y)
      def update(x:Int, y:Int, n:Nivel) = niveles(x)(y) = n
    }

    def velocidad(x:Int, y:Int) = velocidades(x)(y)

    def fuerzasSobre(x:Int, y:Int) : Seq[Fuerza] = {
      val radio = 4
      val kMuelle = 1
      val n = nivel(x,y)
      for( dx <- -radio to radio;
        px = x+dx if(px >= 0 && px < ancho);
        dy <- -radio to radio;
        py = y+dy if(py >= 0 && py < alto && (px!=x ||py!=y) ) ) yield{

        val distancia = (x-px)*(x-px) + (y-py)*(y-py)
        (nivel(px,py)-n) * kMuelle/distancia
        }
    }

    def aceleracion(x:Int, y:Int) = {
      val f = fuerzasSobre(x,y)
      val masa = 1
      f.sum * masa
    }

    def pasoSobre(x:Int, y:Int, intervalo: Paso) : (Nivel,Velocidad) = {
      val viscosidad = 0.9
      val n = nivel(x,y)
      val v = velocidad(x,y)
      val a = aceleracion(x,y)
      (n + v*intervalo, viscosidad*(v+a*intervalo))
    }


    private val nivelesAux = Array.fill(ancho,alto){
      0.asInstanceOf[Nivel]
    }

    private val velocidadesAux = Array.fill(ancho,alto){
      0.asInstanceOf[Velocidad]
    }



    def paso(intervalo: Paso) = {
      for( x <- 0 until ancho ; y <- 0 until alto ){
        val (n,v) = pasoSobre(x,y,intervalo)
        nivelesAux(x)(y)=n
        velocidadesAux(x)(y)=v
      }
      for( x <- 0 until ancho ; y <- 0 until alto ){
        niveles(x)(y) = nivelesAux(x)(y)
        velocidades(x)(y) = velocidadesAux(x)(y)
      }
    }
  } 



}

object AguaMain extends App{
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
