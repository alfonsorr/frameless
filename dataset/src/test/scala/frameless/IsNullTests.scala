package frameless

import org.scalacheck.Prop
import org.scalacheck.Prop._
import org.scalatest.Matchers

class IsNullTests extends TypedDatasetSuite with Matchers{

  test("isNull") {
    def prop[A: TypedEncoder](a: Option[A]): Prop = {
      val df = TypedDataset.create(X1(a) :: Nil)
      val result = a.isEmpty
      val got = df.select(df.col('a).isNull).collect().run()

      got ?= (result :: Nil)
    }

    check(prop[BigDecimal] _)
    check(prop[Byte] _)
    check(prop[Double] _)
    check(prop[Int] _)
    check(prop[Long] _)
    check(prop[Short] _)
    check(prop[String] _)
  }

  test("isNotNull") {
    def prop[A: TypedEncoder](a: Option[A]): Prop = {
      val df = TypedDataset.create(X1(a) :: Nil)
      val result = a.isDefined
      val got = df.select(df.col('a).isNotNull).collect().run()

      got ?= (result :: Nil)
    }

    check(prop[BigDecimal] _)
    check(prop[Byte] _)
    check(prop[Double] _)
    check(prop[Int] _)
    check(prop[Long] _)
    check(prop[Short] _)
    check(prop[String] _)
  }
}
