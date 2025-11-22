# Image Storage Changes

## Overview
Changed from storing images in database to storing them as files.

## Code Changes

### Product.java
```java
// Before
private byte[] imageData;

// After
private String imageUrl;
```

### FileStorageService.java (new)
Handles file uploads and storage.
- Saves images to `uploads/products/`
- Returns URL path like `/api/products/images/abc123.png`

### ProductController.java
Added endpoint to serve images:
```
GET /api/products/images/{filename}
```

## Database Change

Schema update (auto-handled by Hibernate):
- Old: `image_data` column (bytea)
- New: `image_url` column (varchar)

Existing images will be lost. Need to re-upload.

## For Frontend Team

API response changed:

Before:
```json
{
  "imageBase64": "data:image/png;base64,..."
}
```

After:
```json
{
  "imageUrl": "/api/products/images/abc123.png"
}
```

Update your code:
```jsx
// Before
<img src={product.imageBase64} />

// After
<img src={`http://localhost:8080${product.imageUrl}`} />
```

Upload example:
```javascript
const formData = new FormData();
formData.append('product', new Blob([JSON.stringify(productData)], {
  type: 'application/json'
}));
formData.append('file', imageFile);

fetch('http://localhost:8080/api/products', {
  method: 'POST',
  body: formData
});
```

## Testing

Run with H2 database (no PostgreSQL needed):
```bash
java -jar target/ecobazaar-backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2
```

Test upload with Postman:
```
POST http://localhost:8080/api/products
Content-Type: multipart/form-data

Form Data:
- product: {"name":"Test","price":25.99,"sellerId":1,"carbonImpact":2.5}
- file: [image file]
```

## Configuration

Added to application.properties:
```properties
spring.servlet.multipart.max-file-size=10MB
file.upload-dir=uploads/products
```

## Deployment

Make sure `uploads/products/` folder exists and is writable.
