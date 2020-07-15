import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import productsApi from '../../api/productsApi';

export default () => {
  const { id } = useParams();
  const [product, setProduct] = useState({});

  useEffect(() => {
    productsApi.fetchProductById(id)
      .then(resp => setProduct(resp.data));
  }, [id])

  return (
   <div>
     <h1>{product.title}</h1>
     <p>{product.description}</p>
     <p>{product.price}</p>
     {product.fileName &&
      <img
        src={`http://localhost:8080/files/${product.fileName}`}
        alt="Very beautiful product"
      />
      }
   </div>
  )
};