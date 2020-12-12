# TrendingRepositories
Specification of the project:
1.List the trending repositories from remote.
2.Search the repositories using searchview.
3.Pull to refresh  option gives the repositories after performing this operation.
4. Able to check the network connectivity of the app using broadcast receiver.

Libraries Used:
1.Retrofit - For fetching the data from remote.
2.Okhttp- For caching the retrofit data
3.LiveData- For updating the data in activity
4.Roomdatabase- for storing data.



Architecture Pattern:
MVVM acrchitecture pattern  is used throughout the project
![ListofRepositories](https://user-images.githubusercontent.com/38130323/101977339-f805cc80-3c72-11eb-91e6-a2844a4895ab.png)
![Loading_state](https://user-images.githubusercontent.com/38130323/101977544-102a1b80-3c74-11eb-9bf8-a420a1951c29.png)
![EmptyState](https://user-images.githubusercontent.com/38130323/101977547-17512980-3c74-11eb-9efe-b2b7f654959b.png)
![searched data](https://user-images.githubusercontent.com/38130323/101977553-2041fb00-3c74-11eb-9a6b-6d6d904c82b3.png)
![swipe_refresh](https://user-images.githubusercontent.com/38130323/101977556-259f4580-3c74-11eb-8bcd-0436857ce96e.png)
![Afterpullrefresh](https://user-images.githubusercontent.com/38130323/101977564-318b0780-3c74-11eb-8cb2-948e029fc0ff.png)
