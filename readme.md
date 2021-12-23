# DubizzleTest

DubizzleTest is a sample Android application that uses the clean MVVM architecture approach and is written in Kotlin.
(ProductDetailsFragment and ProductViewPagerAdapter classes are written in java)

### Used libraries: ###
- `HILT` - used for dependency injection.
- `ViewModel` - handles data updates and configuration changes.
- `Retrofit` - is used for network calls.
- `Coroutine` - is useful to handle threading.

#### The app has following packages:
1. `data`: It contains all the data accessing classes.
2. `di`: Dependency providing classes using HILT.
4. `presentation`: Activity and fragment classes to show product list and product details along with their corresponding ViewModel.
7. `base`: Android component base classes.
8. `domain`: Domain has single responsibility usecases and interfaces of data layer.

#### Testing
Written unit test cases for mappers, repository and datasource.

#### TODO
- ViewModel test cases are not written.
- UI test cases are still in WIP (They are failing due to image cache dependency.)

#### ASSUMPTIONS
- From the endpoint we get a list of images for each product. 
  Inside the listing screen only a single image is shown.
  When a user clicks on any product then on product details screen view pager is used to show multiple images.
    
- For image caching, all product images are downloaded after api success response.

- Product thumbnails are not used at any places.
 

