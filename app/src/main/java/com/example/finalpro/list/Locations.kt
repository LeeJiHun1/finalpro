package com.example.finalpro.list
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "SaveItem")
data class SaveItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var saveTitle:String
)

data class FreshWrapper(
    @Json(name="list")
    val list:List<Location>
)

@Entity(
    tableName = "Locations",
    foreignKeys = arrayOf(
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("saveId"),
            entity = SaveItem::class
        )
    )
)

data class Location(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var saveId: Long? = null,  //save될때 saveId를 기준으로 호출
    @Json(name = "zipno")//우편번호
    var zipNo: Int,
    @Json(name = "lnmAdres") //도로명 주소
    var lnmAdres: String,
    @Json(name = "rnAdres") //지번 주소
    var rnAdres: String,
    @Json(name = "searchSe")//dong : 동(읍/면)명, road : 도로명 post : 우편 번호
    var searchSe: String
)


//응답 데이터형식 예시

/*"items" : [{
   <zipNo>12621</zipNo>
  <lnmAdres>경기도 여주시 세종로 17 (홍문동)</lnmAdres>
  <rnAdres>경기도 여주시 홍문동 111-15</rnAdres>

}
 */
