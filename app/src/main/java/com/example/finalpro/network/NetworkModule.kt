package com.example.finalpro.network

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

/* object 키워드로 NetworkModule 객체를 생성
   - 공공DB 서버에 요청할 OkHttp Request 객체 생성
 */
object NetworkModule {
    private val keyValue = "itbiCnyof8fKmuSu%2BpwuU%2Fm1AbM6Hnbwuy3iGg%2Fz%2FqxvjGC5R4iSQ6LvrDWcv7PF%2Bw%2FZE5XYn0eZRKOP%2F7acwQ%3D%3D" //키

    /* OKhttp client (실제로 네트워크를 호출하는 부분) 생성 */
    val clinent: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES
            ) // connect timeout // 최근 연결 시간이 길어져 connectTimeout을 2분으로 지정
            .readTimeout(2, TimeUnit.MINUTES) // 최근 읽기 시간이 길어져 readTimeout을 2분으로 지정
            .build()
    }

    /* HttpUrl 객체 정의 함수 선언
       - HttpUrl.Builder()를 이용하여 HttpUrl 객체(URI 구조) 정의
       - 공공DB API 명세에 맞춰 선언
    */
    fun makeHttpUrl(searchSe: String, srchwrd: String): HttpUrl {
        //http://apis.data.go.kr/B552895/StatsInfoService/getDailyAdjStatsInfo

        return HttpUrl.Builder()   //http://openapi.epost.go.kr:80/postal/retrieveNewAdressAreaCdService?_wadl&type=xml
            .scheme("http")
            .host("openapi.epost.go.kr")
            .addPathSegment("postal")
            .addPathSegment("retrieveNewAdressAreaCdService")
            .addPathSegment("retrieveNewAdressAreaCdService")
            .addPathSegment("getNewAddressListAreaCd")
            .addQueryParameter("searchSe",searchSe)
            .addQueryParameter("srchwrd", srchwrd)
            .addQueryParameter("countPerPage", "10")
            .addQueryParameter("currentPage", "1")
            .addQueryParameter("_returnType", "json")
            .build()
    }//end of makeHttpUrl

    /* OkHttp Request 생성 함수
       - Request.Builder()를 사용하여 요청을 위한 Request 생성
       - url: 요청서버 url
       - get/post: 전송방식
    */
    fun makeHttprequest(httpUrl: HttpUrl): Request {
        return Request.Builder()
            /*공공데이터 서버의 문제로 인해 하드코딩하여 KEY값을 넣습니다.
            * - serviceKey에 %가 포함되어 빌더에 설정할 수 없기 때문에 하드 코딩
            * - %가 다른 용도로 사용되고 있기 때문에 키값 인식이 안되는 문제 발생 */
            .url(httpUrl.toString() + "&serviceKey=$keyValue")
            .get().build();
    }//end of makeHttprequest
}//end of NetworkModule