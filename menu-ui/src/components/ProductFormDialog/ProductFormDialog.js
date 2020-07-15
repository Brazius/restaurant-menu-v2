import React, {useState} from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button
} from '@material-ui/core';
import ProductForm from "../../pages/ProductForm/ProductForm";

export default ({open, handleClose}) => {
    return (
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle>Produkto kūrimas</DialogTitle>
            <DialogContent>
                <ProductForm/>
            </DialogContent>
            <DialogActions>
                <Button variant="contained" color="secondary" onClick={handleClose}>Cancel</Button>
                <Button variant="contained" color="primary" form="product-form" type="submit">Create</Button>
            </DialogActions>
        </Dialog>
    )
}