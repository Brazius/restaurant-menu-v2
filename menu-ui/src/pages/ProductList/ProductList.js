import React, {useContext, useEffect, useState} from "react"
import productApi from '../../api/productsApi'
import { NavLink } from 'react-router-dom'
import {
    TableBody,
    Table,
    TableHead,
    TableRow,
    TableCell,
    TableContainer,
    Button,
    Paper,
    TablePagination,
    CircularProgress
} from '@material-ui/core'

import './styles.css';
import {UserContext} from "../../App";
import Secured from "../../components/Secured/Secured";
import ProductFormDialog from "../../components/ProductFormDialog";

export default () => {

    const [productsPage, setProductsPage] = useState({ content: [],});
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(25);
    const [isLoading, setIsLoading] = useState(true);
    const [open, setOpen] = useState(false);

    useEffect(() => {
        productApi.fetchProducts(page, rowsPerPage)
        .then(response => setProductsPage(response.data))
        .finally(() => setIsLoading(false));
    }, [page, rowsPerPage])

    const handleChangeRowsPerPage = (e) => {
        setRowsPerPage(e.target.value);
    }

    const handleChangePage = (e, newPage) => {
        setPage(newPage);
    }

    return (
        <>
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Pavadinimas</TableCell>
                        <TableCell>Kaina</TableCell>
                        <TableCell>Aprašymas</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {isLoading ?
                    <TableRow>
                        <TableCell colSpan="3">
                            <CircularProgress class="loader"/>
                            </TableCell>
                    </TableRow> :
                    productsPage.content.map(product => (
                        <TableRow key={product.title}>
                            <TableCell>{product.title}</TableCell>
                            <TableCell>{product.price}</TableCell>
                            <TableCell>
                                <NavLink to={`products/${product.id}`}>More...</NavLink>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
                <TablePagination
                    rowsPerPageOptions={[25, 50, 100]}
                    rowsPerPage={rowsPerPage}
                    count={productsPage.totalElements}
                    page={page}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                    onChangePage={handleChangePage}
                />
            </Table>
        </TableContainer>
            <Secured>
                {/*<Button variant="contained" color="primary" href="/products/product">Kurti produkta</Button>*/}
                <Button variant="contained" color="primary" onClick={() => setOpen(true)}>Kurti produkta</Button>
            </Secured>
            <ProductFormDialog open={open} handleClose={() => setOpen(false)}/>
       </>
    )
}
