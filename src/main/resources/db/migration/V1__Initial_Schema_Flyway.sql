--

--










SELECT pg_catalog.set_config('search_path', '', false);









--
-- Name: answer_thumbnails; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.answer_thumbnails (
    answer_id bigint NOT NULL,
    thumbnails character varying(255)
);




--
-- Name: answer_votes; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.answer_votes (
    product_id bigint NOT NULL,
    votes bigint
);




--
-- Name: note_category_color; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.note_category_color (
    category_id bigint NOT NULL,
    note_id bigint NOT NULL
);




--
-- Name: post_votes; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.post_votes (
    post_id bigint NOT NULL,
    vote_post_id bigint
);




--
-- Name: product_base_notes; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.product_base_notes (
    note_id bigint NOT NULL,
    product_id bigint NOT NULL
);




--
-- Name: product_colors; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.product_colors (
    color_id bigint NOT NULL,
    product_id bigint NOT NULL
);




--
-- Name: product_compare_compare_votes; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.product_compare_compare_votes (
    compare_votes bigint,
    product_compare_id bigint NOT NULL
);




--
-- Name: product_compare_original_votes; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.product_compare_original_votes (
    original_votes bigint,
    product_compare_id bigint NOT NULL
);




--
-- Name: product_galleries; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.product_galleries (
    product_id bigint NOT NULL,
    galleries character varying(255)
);




--
-- Name: product_middle_notes; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.product_middle_notes (
    note_id bigint NOT NULL,
    product_id bigint NOT NULL
);




--
-- Name: product_outfits; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.product_outfits (
    product_id bigint NOT NULL,
    outfits character varying(255)
);




--
-- Name: product_top_notes; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.product_top_notes (
    note_id bigint NOT NULL,
    product_id bigint NOT NULL
);




--
-- Name: product_votes; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.product_votes (
    product_id bigint NOT NULL,
    votes bigint
);




--
-- Name: tbl_answer; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_answer (
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    question_id bigint,
    updated_at timestamp(6) without time zone,
    user_id bigint
);




--
-- Name: tbl_answer_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_answer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_answer_seq OWNER TO loctran;

--
-- Name: tbl_book; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_book (
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone,
    description character varying(255),
    link character varying(255),
    name character varying(255),
    thumbnail character varying(255)
);




--
-- Name: tbl_book_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_book_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_book_seq OWNER TO loctran;

--
-- Name: tbl_brand; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_brand (
    country_id bigint,
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone,
    description character varying(255),
    homepage_link character varying(255),
    name character varying(255),
    thumbnail character varying(255)
);




--
-- Name: tbl_brand_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_brand_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_brand_seq OWNER TO loctran;

--
-- Name: tbl_collection; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_collection (
    index integer,
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone,
    icon character varying(255),
    title character varying(255)
);




--
-- Name: tbl_collection_product; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_collection_product (
    index integer NOT NULL,
    collection_id bigint,
    id bigint NOT NULL,
    product_id bigint NOT NULL
);




--
-- Name: tbl_collection_product_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_collection_product_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_collection_product_seq OWNER TO loctran;

--
-- Name: tbl_collection_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_collection_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_collection_seq OWNER TO loctran;

--
-- Name: tbl_color; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_color (
    id bigint NOT NULL,
    hex_code character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);




--
-- Name: tbl_color_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_color_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_color_seq OWNER TO loctran;

--
-- Name: tbl_comment; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_comment (
    answer_id bigint,
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    post_id bigint,
    product_compare_id bigint,
    product_id bigint,
    updated_at timestamp(6) without time zone,
    user_id bigint,
    content character varying(255)
);




--
-- Name: tbl_comment_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_comment_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_comment_seq OWNER TO loctran;

--
-- Name: tbl_country; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_country (
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone,
    code character varying(255),
    name character varying(255),
    thumbnail character varying(255)
);




--
-- Name: tbl_country_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_country_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_country_seq OWNER TO loctran;

--
-- Name: tbl_media; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_media (
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone,
    path character varying(255),
    type character varying(50) DEFAULT 'IMAGE'::character varying,
    CONSTRAINT tbl_media_type_check CHECK (((type)::text = 'IMAGE'::text))
);




--
-- Name: tbl_media_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_media_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_media_seq OWNER TO loctran;

--
-- Name: tbl_note_category; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_note_category (
    id bigint NOT NULL,
    description text,
    title character varying(255)
);




--
-- Name: tbl_note_category_id_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

ALTER TABLE public.tbl_note_category ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.tbl_note_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: tbl_post; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_post (
    is_pinned boolean,
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone,
    user_id bigint,
    content text,
    excerpt text,
    slug character varying(255),
    thumbnail character varying(255),
    title character varying(255),
    type character varying(50) DEFAULT 'NEWS'::character varying,
    CONSTRAINT tbl_post_type_check CHECK (((type)::text = ANY ((ARRAY['NEWS'::character varying, 'KNOWLEDGE'::character varying])::text[])))
);




--
-- Name: tbl_post_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_post_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_post_seq OWNER TO loctran;

--
-- Name: tbl_product; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_product (
    brand_id bigint,
    country_id bigint,
    created_at timestamp(6) without time zone,
    date_released_id bigint,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone,
    description text,
    feng_shui text,
    name character varying(255),
    slug character varying(255),
    thumbnail character varying(255)
);




--
-- Name: tbl_product_base_note; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_product_base_note (
    note_id bigint NOT NULL,
    product_id bigint NOT NULL
);




--
-- Name: tbl_product_compare; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_product_compare (
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    product_compare_id bigint,
    product_original_id bigint,
    updated_at timestamp(6) without time zone
);




--
-- Name: tbl_product_compare_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_product_compare_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_product_compare_seq OWNER TO loctran;

--
-- Name: tbl_product_middle_note; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_product_middle_note (
    note_id bigint NOT NULL,
    product_id bigint NOT NULL
);




--
-- Name: tbl_product_note; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_product_note (
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone,
    en_name character varying(255),
    name character varying(255),
    slug character varying(255),
    thumbnail character varying(255)
);




--
-- Name: tbl_product_note_id_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

ALTER TABLE public.tbl_product_note ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.tbl_product_note_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: tbl_product_note_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_product_note_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_product_note_seq OWNER TO loctran;

--
-- Name: tbl_product_price; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_product_price (
    is_search boolean,
    label_type smallint,
    price_type smallint,
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    product_id bigint,
    updated_at timestamp(6) without time zone,
    value bigint,
    link character varying(255),
    CONSTRAINT tbl_product_price_label_type_check CHECK (((label_type >= 0) AND (label_type <= 1))),
    CONSTRAINT tbl_product_price_price_type_check CHECK (((price_type >= 0) AND (price_type <= 1)))
);




--
-- Name: tbl_product_price_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_product_price_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_product_price_seq OWNER TO loctran;

--
-- Name: tbl_product_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_product_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_product_seq OWNER TO loctran;

--
-- Name: tbl_product_tbl_user; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_product_tbl_user (
    tbl_product_id bigint NOT NULL,
    tbl_user_id bigint NOT NULL
);




--
-- Name: tbl_product_top_note; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_product_top_note (
    note_id bigint NOT NULL,
    product_id bigint NOT NULL
);




--
-- Name: tbl_question; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_question (
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone,
    user_id bigint,
    description character varying(255),
    title character varying(255)
);




--
-- Name: tbl_question_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_question_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_question_seq OWNER TO loctran;

--
-- Name: tbl_user; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_user (
    id bigint NOT NULL,
    avatar character varying(255),
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(50) DEFAULT 'USER'::character varying,
    CONSTRAINT tbl_user_role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);




--
-- Name: tbl_user_id_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_user_id_seq OWNER TO loctran;

--
-- Name: tbl_voting; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_voting (
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone
);




--
-- Name: tbl_voting_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_voting_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_voting_seq OWNER TO loctran;

--
-- Name: tbl_year; Type: TABLE; Schema: public; Owner: loctran
--

CREATE TABLE public.tbl_year (
    value integer,
    id bigint NOT NULL
);

--
-- Name: tbl_year_seq; Type: SEQUENCE; Schema: public; Owner: loctran
--

CREATE SEQUENCE public.tbl_year_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tbl_year_seq OWNER TO loctran;

--
-- Name: note_category_color note_category_color_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.note_category_color
    ADD CONSTRAINT note_category_color_pkey PRIMARY KEY (category_id, note_id);


--
-- Name: product_base_notes product_base_notes_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_base_notes
    ADD CONSTRAINT product_base_notes_pkey PRIMARY KEY (note_id, product_id);


--
-- Name: product_colors product_colors_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_colors
    ADD CONSTRAINT product_colors_pkey PRIMARY KEY (color_id, product_id);


--
-- Name: product_middle_notes product_middle_notes_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_middle_notes
    ADD CONSTRAINT product_middle_notes_pkey PRIMARY KEY (note_id, product_id);


--
-- Name: product_top_notes product_top_notes_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_top_notes
    ADD CONSTRAINT product_top_notes_pkey PRIMARY KEY (note_id, product_id);


--
-- Name: tbl_answer tbl_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_answer
    ADD CONSTRAINT tbl_answer_pkey PRIMARY KEY (id);


--
-- Name: tbl_book tbl_book_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_book
    ADD CONSTRAINT tbl_book_pkey PRIMARY KEY (id);


--
-- Name: tbl_brand tbl_brand_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_brand
    ADD CONSTRAINT tbl_brand_pkey PRIMARY KEY (id);


--
-- Name: tbl_collection tbl_collection_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_collection
    ADD CONSTRAINT tbl_collection_pkey PRIMARY KEY (id);


--
-- Name: tbl_collection_product tbl_collection_product_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_collection_product
    ADD CONSTRAINT tbl_collection_product_pkey PRIMARY KEY (id);


--
-- Name: tbl_color tbl_color_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_color
    ADD CONSTRAINT tbl_color_pkey PRIMARY KEY (id);


--
-- Name: tbl_comment tbl_comment_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_comment
    ADD CONSTRAINT tbl_comment_pkey PRIMARY KEY (id);


--
-- Name: tbl_country tbl_country_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_country
    ADD CONSTRAINT tbl_country_pkey PRIMARY KEY (id);


--
-- Name: tbl_media tbl_media_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_media
    ADD CONSTRAINT tbl_media_pkey PRIMARY KEY (id);


--
-- Name: tbl_note_category tbl_note_category_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_note_category
    ADD CONSTRAINT tbl_note_category_pkey PRIMARY KEY (id);


--
-- Name: tbl_post tbl_post_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_post
    ADD CONSTRAINT tbl_post_pkey PRIMARY KEY (id);


--
-- Name: tbl_product_compare tbl_product_compare_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product_compare
    ADD CONSTRAINT tbl_product_compare_pkey PRIMARY KEY (id);


--
-- Name: tbl_product_note tbl_product_note_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product_note
    ADD CONSTRAINT tbl_product_note_pkey PRIMARY KEY (id);


--
-- Name: tbl_product tbl_product_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product
    ADD CONSTRAINT tbl_product_pkey PRIMARY KEY (id);


--
-- Name: tbl_product_price tbl_product_price_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product_price
    ADD CONSTRAINT tbl_product_price_pkey PRIMARY KEY (id);


--
-- Name: tbl_question tbl_question_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_question
    ADD CONSTRAINT tbl_question_pkey PRIMARY KEY (id);


--
-- Name: tbl_user tbl_user_email_key; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_user
    ADD CONSTRAINT tbl_user_email_key UNIQUE (email);


--
-- Name: tbl_user tbl_user_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_user
    ADD CONSTRAINT tbl_user_pkey PRIMARY KEY (id);


--
-- Name: tbl_voting tbl_voting_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_voting
    ADD CONSTRAINT tbl_voting_pkey PRIMARY KEY (id);


--
-- Name: tbl_year tbl_year_pkey; Type: CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_year
    ADD CONSTRAINT tbl_year_pkey PRIMARY KEY (id);


--
-- Name: tbl_comment fk19bt9310a1l6tdyg41v7wbux4; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_comment
    ADD CONSTRAINT fk19bt9310a1l6tdyg41v7wbux4 FOREIGN KEY (product_id) REFERENCES public.tbl_product(id);


--
-- Name: tbl_post fk2xe5ubrtvdd68bg3uf44vcgrt; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_post
    ADD CONSTRAINT fk2xe5ubrtvdd68bg3uf44vcgrt FOREIGN KEY (user_id) REFERENCES public.tbl_user(id);


--
-- Name: tbl_comment fk3yn04en9jpdotwy0ww78jhqs; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_comment
    ADD CONSTRAINT fk3yn04en9jpdotwy0ww78jhqs FOREIGN KEY (answer_id) REFERENCES public.tbl_answer(id);


--
-- Name: answer_thumbnails fk74k8cjdfdkoy3gj5c73tu18tp; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.answer_thumbnails
    ADD CONSTRAINT fk74k8cjdfdkoy3gj5c73tu18tp FOREIGN KEY (answer_id) REFERENCES public.tbl_answer(id);


--
-- Name: product_compare_compare_votes fk7l3en6fu4sjhmqieysus19myc; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_compare_compare_votes
    ADD CONSTRAINT fk7l3en6fu4sjhmqieysus19myc FOREIGN KEY (product_compare_id) REFERENCES public.tbl_product_compare(id);


--
-- Name: tbl_answer fk9g1eq48ckxg43nr81oi3asbm; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_answer
    ADD CONSTRAINT fk9g1eq48ckxg43nr81oi3asbm FOREIGN KEY (question_id) REFERENCES public.tbl_question(id);


--
-- Name: tbl_product_compare fkbk9dkoore1xn154fbfxa7jxd; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product_compare
    ADD CONSTRAINT fkbk9dkoore1xn154fbfxa7jxd FOREIGN KEY (product_original_id) REFERENCES public.tbl_product(id);


--
-- Name: tbl_product fkc8rha6iynnhbb4x3tmmk7x47m; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product
    ADD CONSTRAINT fkc8rha6iynnhbb4x3tmmk7x47m FOREIGN KEY (date_released_id) REFERENCES public.tbl_year(id);


--
-- Name: tbl_product_compare fkcll53c241gswks478h7a5vnrx; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product_compare
    ADD CONSTRAINT fkcll53c241gswks478h7a5vnrx FOREIGN KEY (product_compare_id) REFERENCES public.tbl_product(id);


--
-- Name: post_votes fkfh6ayba1mb0pns103918j89sp; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.post_votes
    ADD CONSTRAINT fkfh6ayba1mb0pns103918j89sp FOREIGN KEY (post_id) REFERENCES public.tbl_post(id);


--
-- Name: product_middle_notes fkfxykawudnoxx3p6qvmsg6xcyc; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_middle_notes
    ADD CONSTRAINT fkfxykawudnoxx3p6qvmsg6xcyc FOREIGN KEY (product_id) REFERENCES public.tbl_product(id);


--
-- Name: note_category_color fkg7gf6a8pbpltuigf86m450mpj; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.note_category_color
    ADD CONSTRAINT fkg7gf6a8pbpltuigf86m450mpj FOREIGN KEY (category_id) REFERENCES public.tbl_note_category(id);


--
-- Name: tbl_comment fkgjlkbaxyvqqrmxeoxh6pb4oj7; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_comment
    ADD CONSTRAINT fkgjlkbaxyvqqrmxeoxh6pb4oj7 FOREIGN KEY (user_id) REFERENCES public.tbl_user(id);


--
-- Name: product_top_notes fkgv0vn1bvfnt8dqk7hoppgx58t; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_top_notes
    ADD CONSTRAINT fkgv0vn1bvfnt8dqk7hoppgx58t FOREIGN KEY (note_id) REFERENCES public.tbl_product_note(id);


--
-- Name: tbl_comment fki7k73l5d2j9cvam2bkepym80k; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_comment
    ADD CONSTRAINT fki7k73l5d2j9cvam2bkepym80k FOREIGN KEY (post_id) REFERENCES public.tbl_post(id);


--
-- Name: tbl_product_tbl_user fkieq0fx2m912vhchkgavkeo48u; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product_tbl_user
    ADD CONSTRAINT fkieq0fx2m912vhchkgavkeo48u FOREIGN KEY (tbl_product_id) REFERENCES public.tbl_product(id);


--
-- Name: tbl_answer fkimmmty6f9rjrmykuvhtvuoeyk; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_answer
    ADD CONSTRAINT fkimmmty6f9rjrmykuvhtvuoeyk FOREIGN KEY (user_id) REFERENCES public.tbl_user(id);


--
-- Name: note_category_color fkj55w1flcdhjkhps70vxohmmul; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.note_category_color
    ADD CONSTRAINT fkj55w1flcdhjkhps70vxohmmul FOREIGN KEY (note_id) REFERENCES public.tbl_product_note(id);


--
-- Name: tbl_product_tbl_user fkjg9qfa0mitjrm9j2ng55tt4q2; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product_tbl_user
    ADD CONSTRAINT fkjg9qfa0mitjrm9j2ng55tt4q2 FOREIGN KEY (tbl_user_id) REFERENCES public.tbl_user(id);


--
-- Name: tbl_brand fkk45sk5yqq87jafwctx0vk0aj7; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_brand
    ADD CONSTRAINT fkk45sk5yqq87jafwctx0vk0aj7 FOREIGN KEY (country_id) REFERENCES public.tbl_country(id);


--
-- Name: tbl_product fklnk3617isqse9ehysrt5aaors; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product
    ADD CONSTRAINT fklnk3617isqse9ehysrt5aaors FOREIGN KEY (brand_id) REFERENCES public.tbl_brand(id);


--
-- Name: tbl_product_price fklrms712rgkbq69kmc9o813sxk; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product_price
    ADD CONSTRAINT fklrms712rgkbq69kmc9o813sxk FOREIGN KEY (product_id) REFERENCES public.tbl_product(id);


--
-- Name: answer_votes fknav3r3c555h7ropumhxubxat2; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.answer_votes
    ADD CONSTRAINT fknav3r3c555h7ropumhxubxat2 FOREIGN KEY (product_id) REFERENCES public.tbl_answer(id);


--
-- Name: tbl_question fknfhxi16d0dbr0fovemahofw89; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_question
    ADD CONSTRAINT fknfhxi16d0dbr0fovemahofw89 FOREIGN KEY (user_id) REFERENCES public.tbl_user(id);


--
-- Name: product_galleries fknk2ne3v90lvao9ycb5oidsmhc; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_galleries
    ADD CONSTRAINT fknk2ne3v90lvao9ycb5oidsmhc FOREIGN KEY (product_id) REFERENCES public.tbl_product(id);


--
-- Name: tbl_comment fko2r9yfynbx6xwyr01qg2rxap3; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_comment
    ADD CONSTRAINT fko2r9yfynbx6xwyr01qg2rxap3 FOREIGN KEY (product_compare_id) REFERENCES public.tbl_product_compare(id);


--
-- Name: tbl_product fkodow9okk1gvb2whec28mm0lqr; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_product
    ADD CONSTRAINT fkodow9okk1gvb2whec28mm0lqr FOREIGN KEY (country_id) REFERENCES public.tbl_country(id);


--
-- Name: product_votes fkp9rilhl38y9nv6u94bwkh2mm6; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_votes
    ADD CONSTRAINT fkp9rilhl38y9nv6u94bwkh2mm6 FOREIGN KEY (product_id) REFERENCES public.tbl_product(id);


--
-- Name: tbl_collection_product fkqlvsul1iam467rni1xrhimw9o; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_collection_product
    ADD CONSTRAINT fkqlvsul1iam467rni1xrhimw9o FOREIGN KEY (product_id) REFERENCES public.tbl_product(id);


--
-- Name: product_colors fkqoanmf0c409a4ec69m55llhlo; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_colors
    ADD CONSTRAINT fkqoanmf0c409a4ec69m55llhlo FOREIGN KEY (product_id) REFERENCES public.tbl_product(id);


--
-- Name: product_top_notes fkre3cyp7cmovg3i1y734pdanp0; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_top_notes
    ADD CONSTRAINT fkre3cyp7cmovg3i1y734pdanp0 FOREIGN KEY (product_id) REFERENCES public.tbl_product(id);


--
-- Name: product_base_notes fkrgpvpdisa87jg9hplsg4tih0u; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_base_notes
    ADD CONSTRAINT fkrgpvpdisa87jg9hplsg4tih0u FOREIGN KEY (product_id) REFERENCES public.tbl_product(id);


--
-- Name: product_middle_notes fkrv7n26dagt7a3drlwgyy150ui; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_middle_notes
    ADD CONSTRAINT fkrv7n26dagt7a3drlwgyy150ui FOREIGN KEY (note_id) REFERENCES public.tbl_product_note(id);


--
-- Name: tbl_collection_product fks7m4puopqivcbmf85nqm7i3w3; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.tbl_collection_product
    ADD CONSTRAINT fks7m4puopqivcbmf85nqm7i3w3 FOREIGN KEY (collection_id) REFERENCES public.tbl_collection(id);


--
-- Name: product_colors fkslm4opuuvor9x7v7lp6iimkcr; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_colors
    ADD CONSTRAINT fkslm4opuuvor9x7v7lp6iimkcr FOREIGN KEY (color_id) REFERENCES public.tbl_color(id);


--
-- Name: product_base_notes fktr3wrncnh775iesbh4jb6wo40; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_base_notes
    ADD CONSTRAINT fktr3wrncnh775iesbh4jb6wo40 FOREIGN KEY (note_id) REFERENCES public.tbl_product_note(id);


--
-- Name: product_compare_original_votes fkujt48iobvthgukcd98o667jf; Type: FK CONSTRAINT; Schema: public; Owner: loctran
--

ALTER TABLE ONLY public.product_compare_original_votes
    ADD CONSTRAINT fkujt48iobvthgukcd98o667jf FOREIGN KEY (product_compare_id) REFERENCES public.tbl_product_compare(id);


--

--

