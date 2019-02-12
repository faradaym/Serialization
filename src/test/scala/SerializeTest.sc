import org.scalatest._

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
    assert(convertToJson(lst) == "[ 1.3, 2.4 ]", "homogeneous list")

  }
  it should "serialize associative collections to objects" in {

  }

}

class HtmlTest extends FlatSpec{
  "Htmlable" should "serialize scalars to text" in {
    val x: String = "peep"
    val y: Double = 3.0
    val z: Char = 'r'
    assert(convertToJson(x) == "peep", "strings")
    assert(convertToJson(y) == "3.0", "nums")
    assert(convertToJson(z) == "r", "chars")
  }
  it should "serialize linear collections to ordered lists" in {
    val lst = 1.3 :: 2.4 ::  Nil
    assert(convertToJson(lst) == "<ol><li>1.3</li><li>2.4</li></ol>", "homogeneous list")
  }
  it should "serialize associative collections to definition lists" in{

  }
}