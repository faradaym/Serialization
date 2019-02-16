object Serialize extends App {

  sealed trait Serial

  object Serial{
    final case class Num(n: Double) extends Serial
    final case class Str(s: String) extends Serial
    final case class Chr(c: Char) extends Serial
    final case class Lst(items: List[Serial]) extends Serial
  }

  trait Serialable[A]{
    def serialize(a: A): String
  }
  object Serialable{
    implicit object SerialableNum extends Serialable[Double]{
      def serialize(a: Double) = a.toString
    }
    implicit object SerialableStr extends Serialable[String]{
      def serialize(a: String) =   "\"" + a + "\""
    }
    implicit object SerialableChar extends Serialable[Char]{
      def serialize(a: Char)= "\"" + a.toString + "\""
    }
    implicit def serializeList[A: Serialable]: Serialable[List[A]]= new Serialable[List[A]]{
      def serialize(lst: List[A]) = {
        val a = lst.map(implicitly[Serialable[A]].serialize)
        a.mkString("[", ", ", "]")
      }
    }
  }

  def convertToJson[A: Serialable](x: A): String = {
    implicitly[Serialable[A]].serialize(x)
  }
  def convertToHtml[A: Serialable](x: A): String = {
    ???
  }

}
