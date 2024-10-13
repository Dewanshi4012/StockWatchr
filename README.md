# StockWatchr 📊
StockWatchr is an Android app for real-time stock market data, featuring live updates, detailed stock information, and offline support for NASDAQ-listed companies on your fingertips. The app is designed to provide users with up-to-date stock data, including price changes, detailed financial metrics, and more. StockWatchr offers a seamless experience whether you are connected to the internet or working offline, with an intuitive design and robust functionality.

## Features 🚀
- **Real-time Stock Data:** Fetches live data from the NASDAQ stock market using Retrofit.
- **Stock Information:** Displays detailed information such as day low/high, opening price, previous close, change percentage, 52-week low/high, market cap, volume, EPS, and P/E ratio.
- **Search Functionality:** SearchView allows users to find specific company stock symbols.
- **Error Handling:** Alerts users when there’s no internet connection, data fetch failure, or invalid stock symbols.
- **Offline Mode:** Cached data is available offline, with automatic updates when reconnected.
- **User-Friendly Design:** A clean UI, with green for increasing stocks and red for decreasing ones.
- **Multi-Screen Support:** Responsive UI that works across different screen sizes.
- **Separation of Concerns:** Follows MVVM architecture for efficient data management and maintainability.


## Screenshots 📷
<img src="https://github.com/user-attachments/assets/34d176db-0ecf-4374-bfce-4b3ed800f3d0" alt="Description"  width="800" />

## Tech Stack 🛠️

- **Languages:** Kotlin, XML
- **Architectural Pattern:** MVVM (Model-View-ViewModel)
- **Libraries:**
  - Retrofit (for API calls)
  - Room Database (for local data storage)
  - ViewBinding (for efficient UI management)
  - Coroutines (for asynchronous tasks)
  - LiveData (for observing data changes)
  - RecyclerView (for displaying stock lists)

## API 💻
StockWatchr pulls real-time data from the _Financial Modeling Prep_ API, which provides up-to-date stock information for companies listed on the NASDAQ exchange. The API delivers detailed stock statistics and supports robust querying to retrieve multiple stock metrics at once.

## Installation and Setup ⚙️
To get started with StockWatchr on your local machine, follow these steps:
- **Clone the repository:**
  
  ```bash
  git clone (https://github.com/Dewanshi4012/StockWatchr.git)
  ```
- Open the project in Android Studio.
- Sync the project with Gradle files to ensure that all dependencies are correctly installed.
- keep your device connected to internet.
- Run the app on an emulator or a physical Android device.

## How the App Works 🔧
### 1.Fetching Data
- StockWatchr uses Retrofit to make API calls to the Financial Modeling Prep API. The data is then stored locally using Room Database, which enables users to view cached stock information when offline.
### 2.Data Display
- The stock data, including the company's symbol, price, change, and other financial details, is displayed in a RecyclerView, which provides an efficient and smooth scrolling experience for long lists.
### 3.Search Functionality
- Users can search for a stock symbol through the SearchView. Upon entering a valid stock symbol, the app will filter the list and display relevant results. Tapping on a stock item navigates the user to a detailed view of the selected stock.
### 4.Error Handling
- The app handles various error scenarios gracefully, displaying user-friendly messages if there is no internet connection or if the API request fails. Additionally, searching for a non-existent stock symbol will result in a clear message indicating that no such stock was found.
### 5.Offline Functionality
- Cached data is displayed if the user is offline. Once the user reconnects to the internet, LiveData automatically updates the UI with the latest stock information from the API.
### 6.Coroutines
- Kotlin Coroutines are used to manage background tasks, such as fetching data from the API, without blocking the UI thread. This ensures that the app remains responsive even during heavy data processing.
  
