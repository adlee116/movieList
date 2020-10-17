package com.example.moviedatabase.viewModel

import com.example.moviedatabase.data.MovieDetails
import com.example.moviedatabase.ui.MovieItemForList
import com.example.moviedatabase.ui.NextPageItemForList
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.hamcrest.CoreMatchers.equalTo
import org.json.JSONObject
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.xml.sax.Parser

class MovieListViewModelTest {

    var movieDetails1 = MovieDetails("1", "/something", "Film Title", "4.5", "19/09/2016", "This is the overview for the movie")
    var movieDetails2 = MovieDetails("1", "/something", "Film Title", "4.5", "19/09/2016", "This is the overview for the movie")
    var movieListItem = MovieItemForList("1", "http://image.tmdb.org/t/p/original/something?api_key=f37fcd6242a1b823af26107652db1803", "Film Title", "4.5", "19/09/2016", "This is the overview for the movie")
    var movieDetailsList = mutableListOf(movieDetails1, movieDetails2)
    var vm = MovieListViewModel()
    lateinit var movieJson: JsonObject

    @Before
    fun setUp() {
        val parser = JsonParser()
        val stringBuilder: String = StringBuilder("{\"page\":1,\"total_results\":10000,\"total_pages\":500,\"results\":[{\"popularity\":3020.903,\"vote_count\":110,\"video\":false,\"poster_path\":\"/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg\",\"id\":528085,\"adult\":false,\"backdrop_path\":\"/5UkzNSOK561c2QRy2Zr4AkADzLT.jpg\",\"original_language\":\"en\",\"original_title\":\"2067\",\"genre_ids\":[878,53],\"title\":\"2067\",\"vote_average\":5.7,\"overview\":\"A lowly utility worker is called to the future by a mysterious radio signal, he must leave his dying wife to embark on a journey that will force him to face his deepest fears in an attempt to change the fabric of reality and save humankind from its greatest environmental crisis yet.\",\"release_date\":\"2020-10-01\"},{\"popularity\":1747.755,\"vote_count\":104,\"video\":false,\"poster_path\":\"/elZ6JCzSEvFOq4gNjNeZsnRFsvj.jpg\",\"id\":741067,\"adult\":false,\"backdrop_path\":\"/aO5ILS7qnqtFIprbJ40zla0jhpu.jpg\",\"original_language\":\"en\",\"original_title\":\"Welcome to Sudden Death\",\"genre_ids\":[28,18,53],\"title\":\"Welcome to Sudden Death\",\"vote_average\":6.7,\"overview\":\"Jesse Freeman is a former special forces officer and explosives expert now working a regular job as a security guard in a state-of-the-art basketball arena. Trouble erupts when a tech-savvy cadre of terrorists kidnap the team's owner and Jesse's daughter during opening night. Facing a ticking clock and impossible odds, it's up to Jesse to not only save them but also a full house of fans in this highly charged action thriller.\",\"release_date\":\"2020-09-29\"},{\"popularity\":1280.395,\"vote_count\":2003,\"video\":false,\"poster_path\":\"/riYInlsq2kf1AWoGm80JQW5dLKp.jpg\",\"id\":497582,\"adult\":false,\"backdrop_path\":\"/kMe4TKMDNXTKptQPAdOF0oZHq3V.jpg\",\"original_language\":\"en\",\"original_title\":\"Enola Holmes\",\"genre_ids\":[80,18,9648],\"title\":\"Enola Holmes\",\"vote_average\":7.6,\"overview\":\"While searching for her missing mother, intrepid teen Enola Holmes uses her sleuthing skills to outsmart big brother Sherlock and help a runaway lord.\",\"release_date\":\"2020-09-23\"},{\"popularity\":881.045,\"vote_count\":2468,\"video\":false,\"poster_path\":\"/aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg\",\"id\":337401,\"adult\":false,\"backdrop_path\":\"/zzWGRw277MNoCs3zhyG3YmYQsXv.jpg\",\"original_language\":\"en\",\"original_title\":\"Mulan\",\"genre_ids\":[28,12,18,14],\"title\":\"Mulan\",\"vote_average\":7.3,\"overview\":\"When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet. Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.\",\"release_date\":\"2020-09-04\"},{\"popularity\":858,\"vote_count\":138,\"video\":false,\"poster_path\":\"/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg\",\"id\":724989,\"adult\":false,\"backdrop_path\":\"/86L8wqGMDbwURPni2t7FQ0nDjsH.jpg\",\"original_language\":\"en\",\"original_title\":\"Hard Kill\",\"genre_ids\":[28,53],\"title\":\"Hard Kill\",\"vote_average\":4.7,\"overview\":\"The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.\",\"release_date\":\"2020-08-25\"},{\"popularity\":851.3,\"vote_count\":139,\"video\":false,\"poster_path\":\"/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg\",\"id\":694919,\"adult\":false,\"backdrop_path\":\"/pq0JSpwyT2URytdFG0euztQPAyR.jpg\",\"original_language\":\"en\",\"original_title\":\"Money Plane\",\"genre_ids\":[28],\"title\":\"Money Plane\",\"vote_average\":6.1,\"overview\":\"A professional thief with \$40 million in debt and his family's life on the line must commit one final heist - rob a futuristic airborne casino filled with the world's most dangerous criminals.\",\"release_date\":\"2020-09-29\"},{\"popularity\":828.499,\"vote_count\":71,\"video\":false,\"poster_path\":\"/xqvX5A24dbIWaeYsMTxxKX5qOfz.jpg\",\"id\":660982,\"adult\":false,\"backdrop_path\":\"/75ooojtgiKYm5LcCczbCexioZze.jpg\",\"original_language\":\"en\",\"original_title\":\"American Pie Presents: Girls' Rules\",\"genre_ids\":[35],\"title\":\"American Pie Presents: Girls Rules\",\"vote_average\":6.5,\"overview\":\"It's Senior year at East Great Falls. Annie, Kayla, Michelle, and Stephanie decide to harness their girl power and band together to get what they want their last year of high school.\",\"release_date\":\"2020-10-06\"},{\"popularity\":730.726,\"vote_count\":5,\"video\":false,\"poster_path\":\"/z0r3YjyJSLqf6Hz0rbBAnEhNXQ7.jpg\",\"id\":697064,\"adult\":false,\"backdrop_path\":\"/7WKIOXJa2JjHygE8Yta3uaCv6GC.jpg\",\"original_language\":\"en\",\"original_title\":\"Beckman\",\"genre_ids\":[28],\"title\":\"Beckman\",\"vote_average\":6.3,\"overview\":\"A contract killer, becomes the reverend of a LA church, until a cult leader and his minions kidnap his daughter. Blinded by vengeance, he cuts a bloody path across the city. The only thing that can stop him is his newfound faith.\",\"release_date\":\"2020-09-10\"},{\"popularity\":702.886,\"vote_count\":136,\"video\":false,\"poster_path\":\"/9Rj8l6gElLpRL7Kj17iZhrT5Zuw.jpg\",\"id\":734309,\"adult\":false,\"backdrop_path\":\"/7fvdg211A2L0mHddvzyArRuRalp.jpg\",\"original_language\":\"en\",\"original_title\":\"Santana\",\"genre_ids\":[28],\"title\":\"Santana\",\"vote_average\":5.6,\"overview\":\"Two brothers — one a narcotics agent and the other a general — finally discover the identity of the drug lord who murdered their parents decades ago. They may kill each other before capturing the bad guys.\",\"release_date\":\"2020-08-28\"},{\"popularity\":702.095,\"vote_count\":304,\"video\":false,\"poster_path\":\"/uOw5JD8IlD546feZ6oxbIjvN66P.jpg\",\"id\":718444,\"adult\":false,\"backdrop_path\":\"/x4UkhIQuHIJyeeOTdcbZ3t3gBSa.jpg\",\"original_language\":\"en\",\"original_title\":\"Rogue\",\"genre_ids\":[28],\"title\":\"Rogue\",\"vote_average\":5.9,\"overview\":\"Battle-hardened O’Hara leads a lively mercenary team of soldiers on a daring mission: rescue hostages from their captors in remote Africa. But as the mission goes awry and the team is stranded, O’Hara’s squad must face a bloody, brutal encounter with a gang of rebels.\",\"release_date\":\"2020-08-20\"},{\"popularity\":687.814,\"vote_count\":481,\"video\":false,\"poster_path\":\"/qzA87Wf4jo1h8JMk9GilyIYvwsA.jpg\",\"id\":539885,\"adult\":false,\"backdrop_path\":\"/54yOImQgj8i85u9hxxnaIQBRUuo.jpg\",\"original_language\":\"en\",\"original_title\":\"Ava\",\"genre_ids\":[28,80,18,53],\"title\":\"Ava\",\"vote_average\":5.9,\"overview\":\"A black ops assassin is forced to fight for her own survival after a job goes dangerously wrong.\",\"release_date\":\"2020-07-02\"},{\"popularity\":631.56,\"vote_count\":605,\"video\":false,\"poster_path\":\"/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg\",\"id\":581392,\"adult\":false,\"backdrop_path\":\"/gEjNlhZhyHeto6Fy5wWy5Uk3A9D.jpg\",\"original_language\":\"ko\",\"original_title\":\"반도\",\"genre_ids\":[28,27,53],\"title\":\"Peninsula\",\"vote_average\":7.1,\"overview\":\"A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.\",\"release_date\":\"2020-07-15\"},{\"popularity\":539.605,\"vote_count\":214,\"video\":false,\"poster_path\":\"/vJHSParlylICnI7DuuI54nfTPRR.jpg\",\"id\":438396,\"adult\":false,\"backdrop_path\":\"/qGZe9qTuydxyJYQ60XDtEckzLR8.jpg\",\"original_language\":\"es\",\"original_title\":\"Orígenes secretos\",\"genre_ids\":[18,53],\"title\":\"Unknown Origins\",\"vote_average\":6.2,\"overview\":\"In Madrid, Spain, a mysterious serial killer ruthlessly murders his victims by recreating the first appearance of several comic book superheroes. Cosme, a veteran police inspector who is about to retire, works on the case along with the tormented inspector David Valentín and his own son Jorge Elías, a nerdy young man who owns a comic book store.\",\"release_date\":\"2020-08-28\"},{\"popularity\":519.52,\"vote_count\":872,\"video\":false,\"poster_path\":\"/tI8ocADh22GtQFV28vGHaBZVb0U.jpg\",\"id\":475430,\"adult\":false,\"backdrop_path\":\"/o0F8xAt8YuEm5mEZviX5pEFC12y.jpg\",\"original_language\":\"en\",\"original_title\":\"Artemis Fowl\",\"genre_ids\":[28,12,14,878,10751],\"title\":\"Artemis Fowl\",\"vote_average\":5.8,\"overview\":\"Artemis Fowl is a 12-year-old genius and descendant of a long line of criminal masterminds. He soon finds himself in an epic battle against a race of powerful underground fairies who may be behind his father's disappearance.\",\"release_date\":\"2020-06-12\"},{\"popularity\":516.521,\"vote_count\":158,\"video\":false,\"poster_path\":\"/xOmGTJtBgRVSAF4S5dZEUqHqyy5.jpg\",\"id\":621870,\"adult\":false,\"backdrop_path\":\"/oSSEcPDfwgZSv2i01Oqxdb9t8fI.jpg\",\"original_language\":\"en\",\"original_title\":\"Secret Society of Second Born Royals\",\"genre_ids\":[28,12,35,14],\"title\":\"Secret Society of Second Born Royals\",\"vote_average\":6.9,\"overview\":\"Sam is a teenage royal rebel, second in line to the throne of the kingdom of Illyria. Just as her disinterest in the royal way of life is at an all-time high, she discovers she has super-human abilities and is invited to join a secret society of similar extraordinary second-born royals charged with keeping the world safe.\",\"release_date\":\"2020-09-25\"},{\"popularity\":510.28,\"vote_count\":394,\"video\":false,\"poster_path\":\"/uQnqhfxWaw2fN3bgpDbO0RVFHLF.jpg\",\"id\":617505,\"adult\":false,\"backdrop_path\":\"/aOeshAxAhiDVIiHsXVFmF6bgclh.jpg\",\"original_language\":\"en\",\"original_title\":\"Hubie Halloween\",\"genre_ids\":[35,27],\"title\":\"Hubie Halloween\",\"vote_average\":6.5,\"overview\":\"Hubie Dubois who, despite his devotion to his hometown of Salem, Massachusetts (and its legendary Halloween celebration), is a figure of mockery for kids and adults alike. But this year, something really is going bump in the night, and it’s up to Hubie to save Halloween.\",\"release_date\":\"2020-10-07\"},{\"popularity\":496.324,\"vote_count\":131,\"video\":false,\"poster_path\":\"/eDnHgozW8vfOaLHzfpHluf1GZCW.jpg\",\"id\":606234,\"adult\":false,\"backdrop_path\":\"/u9YEh2xVAPVTKoaMNlB5tH6pXkm.jpg\",\"original_language\":\"en\",\"original_title\":\"Archive\",\"genre_ids\":[18,878,53],\"title\":\"Archive\",\"vote_average\":5.7,\"overview\":\"2038: George Almore is working on a true human-equivalent AI, and his latest prototype is almost ready. This sensitive phase is also the riskiest as he has a goal that must be hidden at all costs—being reunited with his dead wife.\",\"release_date\":\"2020-08-13\"},{\"popularity\":456.094,\"vote_count\":110,\"video\":false,\"poster_path\":\"/i4kPwXPlM1iy8Jf3S1uuLuwqQAV.jpg\",\"id\":721452,\"adult\":false,\"backdrop_path\":\"/riDrpqQtZpXGeiJdlmfcwwPH7nN.jpg\",\"original_language\":\"en\",\"original_title\":\"One Night in Bangkok\",\"genre_ids\":[28,53],\"title\":\"One Night in Bangkok\",\"vote_average\":7.2,\"overview\":\"A hit man named Kai flies into Bangkok, gets a gun, and orders a cab. He offers a professional female driver big money to be his all-night driver. But when she realizes Kai is committing brutal murders at each stop, it's too late to walk away. Meanwhile, an offbeat police detective races to decode the string of slayings before more blood is spilled.\",\"release_date\":\"2020-08-25\"},{\"popularity\":429.194,\"vote_count\":69,\"video\":false,\"poster_path\":\"/4BgSWFMW2MJ0dT5metLzsRWO7IJ.jpg\",\"id\":726739,\"adult\":false,\"backdrop_path\":\"/t22fWbzdnThPseipsdpwgdPOPCR.jpg\",\"original_language\":\"en\",\"original_title\":\"Cats & Dogs 3: Paws Unite\",\"genre_ids\":[28,35],\"title\":\"Cats & Dogs 3: Paws Unite\",\"vote_average\":6.6,\"overview\":\"It's been ten years since the creation of the Great Truce, an elaborate joint-species surveillance system designed and monitored by cats and dogs to keep the peace when conflicts arise. But when a tech-savvy villain hacks into wireless networks to use frequencies only heard by cats and dogs, he manipulates them into conflict and the worldwide battle between cats and dogs is BACK ON. Now, a team of inexperienced and untested agents will have to use their old-school animal instincts to restore order and peace between cats and dogs everywhere.\",\"release_date\":\"2020-10-02\"},{\"popularity\":428.531,\"vote_count\":1429,\"video\":false,\"poster_path\":\"/TnOeov4w0sTtV2gqICqIxVi74V.jpg\",\"id\":605116,\"adult\":false,\"backdrop_path\":\"/qVygtf2vU15L2yKS4Ke44U4oMdD.jpg\",\"original_language\":\"en\",\"original_title\":\"Project Power\",\"genre_ids\":[28,80,878],\"title\":\"Project Power\",\"vote_average\":6.6,\"overview\":\"An ex-soldier, a teen and a cop collide in New Orleans as they hunt for the source behind a dangerous new pill that grants users temporary superpowers.\",\"release_date\":\"2020-08-14\"}]}").toString()
        movieJson = parser.parse(stringBuilder) as JsonObject
    }

    @Test
    fun createMovieListAddsNextPageItem() {
        val list = vm.createMovieListItems(1, 2, movieDetailsList)
        val lastItem = list[list.lastIndex]
        assertTrue(lastItem is NextPageItemForList)
    }

    @Test
    fun createMovieListHasCorrectAmountOfItems() {
        val list1 = vm.createMovieListItems(1, 2, movieDetailsList)
        movieDetailsList.add(movieDetails1)
        val list2 = vm.createMovieListItems(1, 2, movieDetailsList)
        movieDetailsList.add(movieDetails1)
        val list3 = vm.createMovieListItems(1, 2, movieDetailsList)
        assertTrue(list1.size == 3)
        assertTrue(list2.size == 4)
        assertTrue(list3.size == 5)
    }

    @Test
    fun previousPageNotDisplayedWhenNotRequired() {
        // Page 1 should not have previous page
        val list = vm.createMovieListItems(1, 2, movieDetailsList)
        val lastItem = list[list.lastIndex]
        assertFalse((lastItem as NextPageItemForList).previousAvailable)
    }

    @Test
    fun previousPageDisplayedWhenRequired() {
        // Page 2 should have previous page
        val list = vm.createMovieListItems(2, 2, movieDetailsList)
        val lastItem = list[list.lastIndex]
        assertTrue((lastItem as NextPageItemForList).previousAvailable)
    }

    @Test
    fun nextPageNotDisplayedWhenNotRequired() {
        // No more pages should not show next page button
        val list = vm.createMovieListItems(1, 1, movieDetailsList)
        val lastItem = list[list.lastIndex]
        assertFalse((lastItem as NextPageItemForList).nextAvailable)
    }

    @Test
    fun nextPageDisplayedWhenRequired() {
        // More pages should show next page button
        val list = vm.createMovieListItems(2, 2, movieDetailsList)
        val lastItem = list[list.lastIndex]
        assertFalse((lastItem as NextPageItemForList).nextAvailable)
    }

    @Test
    fun convertMovieToListItemsForAdapterCreatesItemCorrectly() {
        val listItem = vm.convertMovieToListItemsForAdapter(movieDetails1)
        assertThat(listItem.id, equalTo(movieListItem.id))
        assertThat(listItem.originalTitle, equalTo(movieListItem.originalTitle))
        assertThat(listItem.releaseDate, equalTo(movieListItem.releaseDate))
        assertThat(listItem.voteAverage, equalTo(movieListItem.voteAverage))
        assertThat(listItem.posterPath, equalTo(movieListItem.posterPath))
        assertThat(listItem.overview, equalTo(movieListItem.overview))
    }

    @Test
    fun updateUriCorrectly() {
        val fullUri = vm.updateImageUri(movieDetails1.posterPath)
        assertThat(fullUri, equalTo(movieListItem.posterPath))
    }

    @Test
    fun getCurrentPageAndTotalPages() {
        val pages = vm.getCurrentPageAndTotalPages(movieJson)
        assertThat(pages.second, equalTo(500))
        assertThat(pages.first, equalTo(1))
    }

    @Test
    fun convertJsonToMovieList() {
        val convertedList = vm.convertJsonToMovieList(movieJson)
        assertThat(convertedList!!.size, equalTo(20))
    }
}