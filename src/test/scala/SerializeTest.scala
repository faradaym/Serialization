import org.scalatest._
import Serialize._

class JsonTest extends FlatSpec{
  "Jsonable" should "serialize scalars to nums or strings" in{
    val x: String = "peep"
    val y: Double = 3.0
    val z: Char = 'r'
    assert(convertToJson(x) == "\"peep\"", "strings")
    assert(convertToJson(y) == "3.0", "nums")
    assert(convertToJson(z) == "\"r\"", "chars")
  }
  it should "serialize linear collections to arrays" in {
    val lst = 1.3 :: 2.4 ::  Nil
    val lst2 = "PEEEEEEP" :: 87 ::  Nil
    assert(convertToJson(lst) == "[1.3, 2.4]", "homogeneous list")

  }
  it should "serialize associative collections to objects" in {
    val mep = Map("yeehaw" -> 1.3, "sleurp" -> 24.7)
    assert(convertToJson(mep) == "", "homogeneous map" )
  }

}

class HtmlTest extends FlatSpec{
  "Htmlable" should "serialize scalars to text" in {
    val x: String = "peep"
    val y: Double = 3.0
    val z: Char = 'r'
    assert(convertToHtml(x) == "peep", "strings")
    assert(convertToHtml(y) == "3.0", "nums")
    assert(convertToHtml(z) == "r", "chars")
  }
  it should "serialize linear collections to ordered lists" in {
    val lst = 1.3 :: 2.4 ::  Nil
    assert(convertToHtml(lst) == "<ol><li>1.3</li><li>2.4</li></ol>", "homogeneous list")
  }
//  it should "serialize associative collections to definition lists" in{
//
//  }
}