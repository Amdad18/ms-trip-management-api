/*
 * This file is generated by jOOQ.
 */
package org.devp.trip.model.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Transporters implements Serializable {

    private static final long serialVersionUID = 1L;

    private ULong         id;
    private ULong         userId;
    private String        name;
    private String        email;
    private String        address;
    private String        phone;
    private String        profileImage;
    private Byte          status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Transporters() {}

    public Transporters(Transporters value) {
        this.id = value.id;
        this.userId = value.userId;
        this.name = value.name;
        this.email = value.email;
        this.address = value.address;
        this.phone = value.phone;
        this.profileImage = value.profileImage;
        this.status = value.status;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public Transporters(
        ULong         id,
        ULong         userId,
        String        name,
        String        email,
        String        address,
        String        phone,
        String        profileImage,
        Byte          status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.profileImage = profileImage;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Getter for <code>trip_management.transporters.id</code>.
     */
    public ULong getId() {
        return this.id;
    }

    /**
     * Setter for <code>trip_management.transporters.id</code>.
     */
    public Transporters setId(ULong id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>trip_management.transporters.user_id</code>.
     */
    public ULong getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>trip_management.transporters.user_id</code>.
     */
    public Transporters setUserId(ULong userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Getter for <code>trip_management.transporters.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>trip_management.transporters.name</code>.
     */
    public Transporters setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Getter for <code>trip_management.transporters.email</code>.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for <code>trip_management.transporters.email</code>.
     */
    public Transporters setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Getter for <code>trip_management.transporters.address</code>.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Setter for <code>trip_management.transporters.address</code>.
     */
    public Transporters setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Getter for <code>trip_management.transporters.phone</code>.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Setter for <code>trip_management.transporters.phone</code>.
     */
    public Transporters setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Getter for <code>trip_management.transporters.profile_image</code>.
     */
    public String getProfileImage() {
        return this.profileImage;
    }

    /**
     * Setter for <code>trip_management.transporters.profile_image</code>.
     */
    public Transporters setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    /**
     * Getter for <code>trip_management.transporters.status</code>. 0 = PENDING, 1 = ACTIVE, 2 = INACTIVE, 3 = CANCEL_ACCOUNT, 4 = DELETE_ACCOUNT, 5 = ARCHIVE, 6 = REMOVED
     */
    public Byte getStatus() {
        return this.status;
    }

    /**
     * Setter for <code>trip_management.transporters.status</code>. 0 = PENDING, 1 = ACTIVE, 2 = INACTIVE, 3 = CANCEL_ACCOUNT, 4 = DELETE_ACCOUNT, 5 = ARCHIVE, 6 = REMOVED
     */
    public Transporters setStatus(Byte status) {
        this.status = status;
        return this;
    }

    /**
     * Getter for <code>trip_management.transporters.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>trip_management.transporters.created_at</code>.
     */
    public Transporters setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Getter for <code>trip_management.transporters.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>trip_management.transporters.updated_at</code>.
     */
    public Transporters setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Transporters other = (Transporters) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!userId.equals(other.userId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        }
        else if (!email.equals(other.email))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        }
        else if (!address.equals(other.address))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        }
        else if (!phone.equals(other.phone))
            return false;
        if (profileImage == null) {
            if (other.profileImage != null)
                return false;
        }
        else if (!profileImage.equals(other.profileImage))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        }
        else if (!status.equals(other.status))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!createdAt.equals(other.createdAt))
            return false;
        if (updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        }
        else if (!updatedAt.equals(other.updatedAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.address == null) ? 0 : this.address.hashCode());
        result = prime * result + ((this.phone == null) ? 0 : this.phone.hashCode());
        result = prime * result + ((this.profileImage == null) ? 0 : this.profileImage.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Transporters (");

        sb.append(id);
        sb.append(", ").append(userId);
        sb.append(", ").append(name);
        sb.append(", ").append(email);
        sb.append(", ").append(address);
        sb.append(", ").append(phone);
        sb.append(", ").append(profileImage);
        sb.append(", ").append(status);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);

        sb.append(")");
        return sb.toString();
    }
}
