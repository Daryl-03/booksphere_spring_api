# BookSphere API

BookSphere is a comprehensive book catalog and recommendation system built on Spring Boot 3.1.4 with Java 17. The system provides a robust REST API for managing extensive book collections, tracking user engagement through ratings and reading history, and delivering personalized book recommendations powered by machine learning.

## Technical Architecture

The application follows a **layered architecture pattern** with clear separation of concerns:

- **Presentation Layer**: REST controllers (`BookController`, `RecommendationController`) handling HTTP requests with comprehensive Swagger documentation
- **Service Layer**: Business logic encapsulation in services like `BookService` and `RecommendationService` with transaction management
- **Data Access Layer**: Spring Data JPA repositories providing database abstraction
- **Domain Model**: Rich entity relationships with many-to-many associations between books, authors, and genres 

## Core Capabilities

### Advanced Book Management
- **Multi-dimensional Search**: Full-text search across titles, authors, genres, and ISBN with intelligent result merging
- **Sophisticated Pagination**: Configurable page sizes (15 for general, 10 for author searches) with rating-based sorting
- **Composite Queries**: Complex searches combining multiple criteria with optimized database queries

### User Engagement System
- **Firebase Authentication**: Secure user authentication using Firebase Admin SDK 9.2.0 
- **Rating Engine**: Real-time rating aggregation with automatic book rating updates  
- **Reading History Tracking**: Comprehensive user activity monitoring for personalization

### ML-Powered Recommendations
- **External Integration**: Seamless integration with Flask-based ML recommendation service
- **Data Pipeline**: Aggregates user history and ratings to generate personalized recommendations  
- **Fallback Handling**: Graceful degradation when external services are unavailable

## Technology Stack

- **Framework**: Spring Boot 3.1.4 with Spring Security, Spring Data JPA, and Spring Web MVC
- **Database**: MySQL/MariaDB with dual driver support for flexibility  
- **Authentication**: Firebase Admin SDK for token-based authentication
- **Documentation**: Springdoc OpenAPI 2.1.0 for interactive API documentation 
- **Build Tool**: Gradle with comprehensive dependency management
- **Code Quality**: Lombok for boilerplate reduction and enhanced developer experience

## Security Architecture

The system implements **selective authentication** through `FirebaseAuthenticationFilter` with public endpoints for book browsing and protected endpoints for user-specific operations . This approach balances accessibility with security, allowing anonymous book discovery while protecting user data.

## Documentation
- **Documentation:** [Consult the documentation](https://deepwiki.com/Daryl-03/booksphere_spring_api)

The system implements **selective authentication** through `FirebaseAuthenticationFilter` with public endpoints for book browsing and protected endpoints for user-specific operations . This approach balances accessibility with security, allowing anonymous book discovery while protecting user data.

## Related Repositories

- **Mobile App**: [Mobile App Repository](https://github.com/Daryl-03/booksphere) - Cross-platform mobile client for BookSphere
- **Flask Recommendation API**: [Flask ML Service](https://github.com/Daryl-03/book_recommender_flask) - Python-based machine learning recommendation engine
