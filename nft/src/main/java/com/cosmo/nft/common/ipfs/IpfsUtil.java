package com.cosmo.nft.common.ipfs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IpfsUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(IpfsUtil.class);
	
	private final IPFSConfig ipfsConfig;

    public String saveFile(MultipartFile file) {

        try {
            InputStream inputStream = new ByteArrayInputStream(file.getBytes());
            IPFS ipfs = ipfsConfig.ipfs;
            NamedStreamable.InputStreamWrapper is = new NamedStreamable.InputStreamWrapper(inputStream);
            MerkleNode response = ipfs.add(is).get(0);
            
            logger.info("Hash (base 58): " + response.name.get() + " - " + response.hash.toBase58());
            
            return response.hash.toBase58();

        } catch (IOException ex) {
            throw new RuntimeException("Error whilst communicating with the IPFS node", ex);
            
        }

    }

    public byte[] loadFile(String hash) {
        try {
            IPFS ipfs = ipfsConfig.ipfs;
            Multihash filePointer = Multihash.fromBase58(hash);

            return ipfs.cat(filePointer);
            
        } catch (IOException ex) {
            throw new RuntimeException("Error whilst communicating with the IPFS node", ex);
            
        }
    }

}
