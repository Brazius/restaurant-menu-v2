import HTTP from '.'

export default {
    fetchProducts(pageNumber, pageSize) {
        return HTTP.get(`/v2/products?pageNumber=${pageNumber}&pageSize=${pageSize}`)
    },
    fetchProductById(id) {
        return HTTP.get(`/v1/products/${id}`);
    },
    createProduct(product, file) {
        let data = new FormData();
        data.append("file", file);
        data.append("title", product.title);
        data.append("description", product.description);
        data.append("price", product.price);
        return HTTP.post('/v1/products/product', data);
    }
}
