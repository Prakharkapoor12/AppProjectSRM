import tkinter as tk
from tkinter import messagebox, simpledialog
import json

inventory = {
    'Laptop': 10,
    'Smartphone': 0,
    'Headphones': 25,
    'Monitor': 5,
    'Keyboard': 15,
}


def check_availability():
    product = entry.get().strip()
    
    if product in inventory:
        quantity = inventory[product]
        if quantity > 0:
            messagebox.showinfo("Product Availability", f"{product}: {quantity} in stock")
        else:
            messagebox.showinfo("Product Availability", f"{product}: Out of Stock")
    else:
        messagebox.showwarning("Error", "Product not found in the inventory.")


def add_product():
    product = simpledialog.askstring("Input", "Enter product name:").strip()
    if product and product not in inventory:
        try:
            quantity = int(simpledialog.askstring("Input", "Enter stock quantity:"))
            inventory[product] = quantity
            messagebox.showinfo("Success", f"Product {product} added with quantity {quantity}.")
        except ValueError:
            messagebox.showwarning("Error", "Please enter a valid number for quantity.")
    else:
        messagebox.showwarning("Error", "Product already exists or invalid name.")

def remove_product():
    product = simpledialog.askstring("Input", "Enter product name to remove:").strip()
    if product in inventory:
        del inventory[product]
        messagebox.showinfo("Success", f"Product {product} removed.")
    else:
        messagebox.showwarning("Error", "Product not found.")


def update_stock():
    product = simpledialog.askstring("Input", "Enter product name:").strip()
    if product in inventory:
        try:
            quantity = int(simpledialog.askstring("Input", "Enter new stock quantity:"))
            inventory[product] = quantity
            messagebox.showinfo("Success", f"Stock for {product} updated to {quantity}.")
        except ValueError:
            messagebox.showwarning("Error", "Please enter a valid number for quantity.")
    else:
        messagebox.showwarning("Error", "Product not found.")

def save_inventory():
    with open("inventory.json", "w") as file:
        json.dump(inventory, file)
    messagebox.showinfo("Success", "Inventory saved to inventory.json.")

def load_inventory():
    global inventory
    try:
        with open("inventory.json", "r") as file:
            inventory = json.load(file)
        messagebox.showinfo("Success", "Inventory loaded from inventory.json.")
    except (FileNotFoundError, json.JSONDecodeError):
        messagebox.showwarning("Error", "Failed to load inventory file.")


root = tk.Tk()
root.title("Enhanced Product Availability Checker")
root.geometry("400x300")

label = tk.Label(root, text="Enter product name:", font=('Arial', 12))
label.pack(pady=10)

entry = tk.Entry(root, width=30)
entry.pack(pady=10)

check_button = tk.Button(root, text="Check Availability", command=check_availability)
check_button.pack(pady=5)

add_button = tk.Button(root, text="Add Product", command=add_product)
add_button.pack(pady=5)

remove_button = tk.Button(root, text="Remove Product", command=remove_product)
remove_button.pack(pady=5)

update_button = tk.Button(root, text="Update Stock", command=update_stock)
update_button.pack(pady=5)


save_button = tk.Button(root, text="Save Inventory", command=save_inventory)
save_button.pack(pady=5)

load_button = tk.Button(root, text="Load Inventory", command=load_inventory)
load_button.pack(pady=5)

root.mainloop()
