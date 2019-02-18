object Serialize extends App {

  sealed trait Serial

  object Serial{
    final case class Num(n: Double) extends Serial
    final case class Str(s: String) extends Serial
    final case class Chr(c: Char) extends Serial
    final case class Lst(items: List[Serial]) extends Serial
  }

  trait JSON[A]{
    def serialize(a: A): String
  }
  object JSON{
    implicit object JSONNum extends JSON[Double]{
      def serialize(a: Double) = a.toString
    }
    implicit object JSONStr extends JSON[String]{
      def serialize(a: String) =   "\"" + a + "\""
    }
    implicit object JSONChar extends JSON[Char]{
      def serialize(a: Char)= "\"" + a.toString + "\""
    }
    implicit def serializeList[A: JSON]: JSON[List[A]]= new JSON[List[A]]{
      def serialize(lst: List[A]) = {
        val a = lst.map(implicitly[JSON[A]].serialize)
        a.mkString("[", ", ", "]")
      }
    }
  }

  trait HTML[A]{
    def serialize(a: A): String
  }
  object HTML{
    implicit object HTMLNum extends HTML[Double]{
      def serialize(a: Double) = a.toString
    }
    implicit object HTMLStr extends HTML[String]{
      def serialize(a: String) = a.toString
    }
    implicit object HTMLChar extends HTML[Char]{
      def serialize(a: Char)= a.toString
    }
    implicit def serializeList[A: HTML]: HTML[List[A]]= new HTML[List[A]]{
      def serialize(lst: List[A]) = {
        val a = lst.map(implicitly[HTML[A]].serialize)
        a.map("<li>" + _ + "</li>").mkString("<ol>", "", "</ol>")
      }
    }
  }

  def convertToJson[A: JSON](x: A): String = {
    implicitly[JSON[A]].serialize(x)
  }
  def convertToHtml[A: HTML](x: A): String = {
    implicitly[HTML[A]].serialize(x)
  }

}
