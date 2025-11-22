# Image Storage Migration

## Summary
Changed product image storage from database to file system for better performance.

## What Changed

**Product Entity:**
- Removed: `byte[] imageData`
- Added: `String imageUrl`

**New Service:**
- Added `FileStorageService` to handle file uploads
- Images saved to `uploads/products/` folder

**API Changes:**
- Added endpoint: `GET /api/products/images/{filename}`
- Response now returns `imageUrl` instead of `imageBase64`

**Database:**
- Column changed: `image_data` → `image_url`
- Hibernate will auto-update schema on startup

**Configuration:**
- Added H2 database profile for testing
- Added file upload config (max 10MB)

## Breaking Changes

Frontend needs to update image display:

Before:
```jsx
<img src={product.imageBase64} />
```

After:
```jsx
<img src={`http://localhost:8080${product.imageUrl}`} />
```

## Testing

Tested with H2 database. All features working.

Run with H2:
```bash
java -jar target/ecobazaar-backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2
```

## Files Modified
- Product.java
- ProductService.java
- ProductController.java
- ProductResponseDTO.java
- FileStorageService.java (new)
- application.properties
- application-h2.properties (new)
- pom.xml
