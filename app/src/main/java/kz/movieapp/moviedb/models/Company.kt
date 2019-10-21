package kz.movieapp.moviedb.models

data class Company(
    val name: String,
    val logo_path: String
) {
    fun getCompanyLogoUrl(): String {
        if(logo_path == null){
            return "https://image.freepik.com/free-vector/404_8024-4.jpg"
        } else {
            return "http://image.tmdb.org/t/p/w500$logo_path"
        }
    }
}
